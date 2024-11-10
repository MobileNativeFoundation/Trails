package org.mobilenativefoundation.trails.backend.server

import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.mobilenativefoundation.trails.backend.server.plugins.configureRouting
import org.mobilenativefoundation.trails.backend.server.plugins.configureSerialization
import org.mobilenativefoundation.trails.backend.server.plugins.configureWebSockets


fun main(args: Array<String>): Unit =
    EngineMain.main(args)

fun Application.module() {
    val database = createTrailsDatabase()

    configureSerialization()
    configureWebSockets()
    configureRouting(database)
}

