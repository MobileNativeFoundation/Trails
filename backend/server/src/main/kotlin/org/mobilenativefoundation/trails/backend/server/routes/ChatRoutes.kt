package org.mobilenativefoundation.trails.backend.server.routes

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.datetime.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.mobilenativefoundation.trails.backend.server.TrailsDatabase
import java.util.concurrent.ConcurrentHashMap

class ChatRoutes(private val trailsDatabase: TrailsDatabase) {

    private val chatSessionConnections = ConcurrentHashMap<Int, ConcurrentHashMap<Int, DefaultWebSocketServerSession>>()

    fun Route.chat() {
        webSocket("/chat/{chatSessionId}/{userId}") {
            val chatSessionId = call.parameters["chatSessionId"]?.toIntOrNull()
            val userId = call.parameters["userId"]?.toIntOrNull()

            if (userId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid or missing user ID.")
                return@webSocket
            }

            if (chatSessionId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid or missing chat session ID.")
                return@webSocket
            }

            val chatSessionParticipantIds =
                trailsDatabase.chatQueries.getParticipantsByChatSessionId(chatSessionId).executeAsList()
            val isParticipant = userId in chatSessionParticipantIds

            if (!isParticipant) {
                close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "Unauthorized"))
                return@webSocket
            }

            val connections = chatSessionConnections.getOrPut(chatSessionId) {
                ConcurrentHashMap()
            }

            connections[userId] = this

            try {
                incoming.consumeEach { frame ->
                    if (frame is Frame.Text) {
                        val receivedText = frame.readText()
                        val chatMessage = Json.decodeFromString<ChatMessage>(receivedText)

                        // Store the message in the database
                        trailsDatabase.chatQueries.insertChatMessage(
                            chat_session_id = chatSessionId,
                            from_user_id = userId,
                            content = chatMessage.content,
                            timestamp = chatMessage.timestamp.toJavaLocalDateTime()
                        )

                        // Send the message to all active participants in the chat session
                        connections.forEach { (participantId, session) ->
                            if (participantId != userId) {
                                session.send(
                                    Frame.Text(
                                        Json.encodeToString(chatMessage)
                                    )
                                )
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                println("Error for user $userId: ${e.localizedMessage}.")
            } finally {
                print("User disconnected: $userId.")
                connections.remove(userId)
                if (connections.isEmpty()) {
                    chatSessionConnections.remove(chatSessionId)
                }
            }

        }
    }
}

@Serializable
data class ChatMessage(
    val chatSessionId: Int,
    val fromUserId: Int,
    val content: String,
    val timestamp: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
)