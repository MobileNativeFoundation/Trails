package org.mobilenativefoundation.trails.server.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText(
                """
                Trails
                An offline-first companion. Roam freely. Explore more. 
            """.trimIndent()
            )
        }
    }
}
