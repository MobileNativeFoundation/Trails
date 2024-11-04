package org.mobilenativefoundation.trails.backend.server.plugins

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.query.Query

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(
            json = Json {
                serializersModule = SerializersModule {
                    contextual(String::class, String.serializer())
                    contextual(Int::class, Int.serializer())
                    contextual(Long::class, Long.serializer())
                    contextual(Boolean::class, Boolean.serializer())
                }
            },
            contentType = ContentType.Application.Json
        )
    }
}