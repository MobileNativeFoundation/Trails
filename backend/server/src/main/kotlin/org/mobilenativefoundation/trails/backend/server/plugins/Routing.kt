package org.mobilenativefoundation.trails.backend.server.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import kotlinx.datetime.toKotlinLocalDateTime
import org.mobilenativefoundation.trails.backend.models.Post
import org.mobilenativefoundation.trails.backend.server.TrailsDatabase

fun Application.configureRouting(
    database: TrailsDatabase
) {

    routing {
        get("/posts") {

            try {
                val posts = database.postQueries.selectPosts(20).executeAsList().map { entity ->
                    Post(
                        id = entity.id,
                        creatorId = entity.creator_id,
                        caption = entity.caption,
                        platform = entity.platform,
                        createdAt = entity.created_at.toKotlinLocalDateTime(),
                        likesCount = entity.likes_count,
                        commentsCount = entity.comments_count,
                        sharesCount = entity.shares_count,
                        viewsCount = entity.views_count,
                        isSponsored = entity.is_sponsored,
                        locationName = entity.location_name,
                        coverUrl = entity.cover_url,
                    )
                }

                call.respond(posts)

            } catch (error: Throwable) {
                call.respond(error.message, TypeInfo(Throwable::class))
            }
        }
    }
}