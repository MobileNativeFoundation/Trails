package org.mobilenativefoundation.trails.backend.server

import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.mobilenativefoundation.trails.backend.server.plugins.configureRouting
import org.mobilenativefoundation.trails.backend.server.plugins.configureSerialization


fun main(args: Array<String>): Unit =
    EngineMain.main(args)

fun Application.module() {
    val database = createTrailsDatabase()

    configureSerialization()
    configureRouting(database)
}

