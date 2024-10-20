package org.mobilenativefoundation.trails.backend

import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.mobilenativefoundation.trails.backend.plugins.configureRouting
import org.mobilenativefoundation.trails.backend.plugins.configureSerialization


fun main(args: Array<String>): Unit =
    EngineMain.main(args)

fun Application.module() {
    val database = createTrailsDatabase()

    configureSerialization()
    configureRouting(database)
}

