package org.mobilenativefoundation.trails.server

import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.litote.kmongo.KMongo
import org.mobilenativefoundation.trails.server.plugins.configureHTTP
import org.mobilenativefoundation.trails.server.plugins.configureRouting
import org.mobilenativefoundation.trails.server.plugins.configureSerialization

fun main() {
    EngineMain.main(arrayOf())
}

fun Application.module() {
    val mongoDbUri = environment.config.propertyOrNull("ktor.security.mongodb.uri")?.getString() ?: return

    val client = KMongo.createClient(mongoDbUri)
    val database = client.getDatabase("Trails")

    println("🚀 Connected to Trails database!")

    configureHTTP()
    configureSerialization()
    configureRouting()
}