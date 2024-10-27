package org.mobilenativefoundation.trails.backend.server.routes

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.datetime.toKotlinLocalDateTime
import org.mobilenativefoundation.trails.backend.models.CompositeCreator
import org.mobilenativefoundation.trails.backend.models.Creator
import org.mobilenativefoundation.trails.backend.models.Post
import org.mobilenativefoundation.trails.backend.server.TrailsDatabase
import org.mobilenativefoundation.trails.backend.server.models.GetCompositeCreatorById

class CreatorRoutes(private val database: TrailsDatabase) {
    fun Route.getCreatorById() {
        get("/creators/{creatorId}") {
            val creatorId = call.parameters["creatorId"]?.toIntOrNull()
            if (creatorId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid or missing creator ID")
                return@get
            }

            val rowsForCreator = database.postQueries.getCompositeCreatorById(creatorId).executeAsList()
            if (rowsForCreator.isEmpty()) {
                call.respond(HttpStatusCode.NotFound, "Creator not found")
                return@get
            }

            val creator = buildCompositeCreator(rowsForCreator, creatorId)
            call.respond(creator)
        }
    }

    private fun buildCompositeCreator(rowsForCreator: List<GetCompositeCreatorById>, creatorId: Int): CompositeCreator {
        val entity = rowsForCreator.first()

        val creator = Creator(
            id = entity.creator_id,
            username = entity.creator_username,
            fullName = entity.creator_full_name,
            profilePicUrl = entity.creator_profile_pic_url,
            isVerified = entity.creator_is_verified,
            bio = entity.creator_bio,
            platform = entity.creator_platform
        )

        val posts = rowsForCreator.mapNotNull { row ->
            row.post_id?.let { postId ->
                row.post_platform?.let { platform ->
                    row.post_created_at?.let { createdAt ->
                        row.post_likes_count?.let { likesCount ->
                            row.post_comments_count?.let { commentsCount ->
                                row.post_shares_count?.let { sharesCount ->
                                    row.post_views_count?.let { viewsCount ->
                                        row.post_is_sponsored?.let { isSponsored ->
                                            row.post_cover_url?.let { coverURL ->
                                                Post(
                                                    id = postId,
                                                    creatorId = creatorId,
                                                    caption = row.post_caption,
                                                    platform = platform,
                                                    createdAt = createdAt.toKotlinLocalDateTime(),
                                                    likesCount = likesCount,
                                                    commentsCount = commentsCount,
                                                    sharesCount = sharesCount,
                                                    viewsCount = viewsCount,
                                                    isSponsored = isSponsored,
                                                    locationName = row.post_location_name,
                                                    coverUrl = coverURL,
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        return CompositeCreator(creator, posts)
    }

}