package org.mobilenativefoundation.trails.backend.server.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import kotlinx.datetime.toKotlinLocalDateTime
import org.mobilenativefoundation.trails.backend.models.*
import org.mobilenativefoundation.trails.backend.server.TrailsDatabase

fun Application.configureRouting(
    database: TrailsDatabase
) {

    routing {
        get("/posts") {

            try {

                val postIds = database.postQueries.selectPosts(8).executeAsList().map { it.id }


                val posts = mutableListOf<PopulatedPost>()

                for (postId in postIds) {
                    val rows = database.postQueries.getPopulatedPostById(postId).executeAsList()

                    val entity = rows[0]

                    val post = Post(
                        id = entity.post_id,
                        creatorId = entity.post_creator_id,
                        caption = entity.post_caption,
                        platform = entity.post_platform,
                        createdAt = entity.post_created_at.toKotlinLocalDateTime(),
                        likesCount = entity.post_likes_count,
                        commentsCount = entity.post_comments_count,
                        sharesCount = entity.post_shares_count,
                        viewsCount = entity.post_views_count,
                        isSponsored = entity.post_is_sponsored,
                        locationName = entity.post_location_name,
                        coverUrl = entity.post_cover_url,
                    )

                    val creator = Creator(
                        id = entity.creator_id ?: error("Missing creator ID"),
                        username = entity.creator_username ?: error("Missing creator username"),
                        fullName = entity.creator_full_name,
                        profilePicUrl = entity.creator_profile_pic_url,
                        isVerified = entity.creator_is_verified ?: false,
                        bio = entity.creator_bio,
                        platform = entity.creator_platform ?: error("Missing creator platform")
                    )

                    val hashtags = mutableListOf<Hashtag>()

                    for (row in rows) {
                        val id = row.hashtag_id
                        val name = row.hashtag_name
                        if (id != null && name != null) {
                            hashtags.add(Hashtag(id, name))
                        }
                    }

                    val mentions = mutableListOf<Mention>()
                    for (row in rows) {
                        val id = row.mention_id
                        val platform = row.mention_platform
                        val mentionedUsername = row.mention_mentioned_username
                        if (id != null && platform != null && mentionedUsername != null) {
                            mentions.add(Mention(id, postId, mentionedUsername, platform))
                        }
                    }

                    val media = mutableListOf<Media>()
                    for (row in rows) {
                        val id = row.media_id
                        val mediaURL = row.media_media_url
                        val mediaType = row.media_media_type
                        val height = row.media_height
                        val width = row.media_width
                        val duration = row.media_duration
                        val altText = row.media_alt_text
                        val mediaFormat = row.media_media_format

                        if (id != null && mediaURL != null && mediaType != null) {
                            media.add(
                                Media(
                                    id, postId, mediaURL, mediaType, height, width, duration, altText, mediaFormat
                                )
                            )
                        }

                    }

                    posts.add(
                        PopulatedPost(
                            post, creator, hashtags, mentions, media
                        )
                    )
                }

                call.respond(posts)

            } catch (error: Throwable) {
                call.respond(error.message, TypeInfo(Throwable::class))
            }
        }

        get("/post/{postId}") {

            val postId = call.parameters["postId"]?.toInt()
            val post = postId?.let {
                database.postQueries.selectPostById(postId).executeAsOneOrNull()
            }

            if (post != null) {
                call.respond(post)
            } else {
                call.respond(404)
            }
        }
    }
}