package org.mobilenativefoundation.trails.backend.server.plugins

import io.ktor.server.application.*
import io.ktor.server.websocket.*

fun Application.configureWebSockets() {
    install(WebSockets)
}