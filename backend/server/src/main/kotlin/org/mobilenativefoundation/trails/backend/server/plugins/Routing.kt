package org.mobilenativefoundation.trails.backend.server.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.mobilenativefoundation.trails.backend.server.TrailsDatabase
import org.mobilenativefoundation.trails.backend.server.routes.PostRoutes

fun Application.configureRouting(
    database: TrailsDatabase
) {

    val postRoutes = PostRoutes(database)

    routing {
        with(postRoutes) {
            getPostById()
            getPosts()
        }
    }
}