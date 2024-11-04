package org.mobilenativefoundation.trails.backend.server.routes

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.datetime.toKotlinLocalDateTime
import org.mobilenativefoundation.trails.backend.server.TrailsDatabase
import org.mobilenativefoundation.trails.backend.server.GetCompositePostById
import org.mobilenativefoundation.trails.xplat.lib.models.post.Creator
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post

class PostRoutes(private val database: TrailsDatabase) {

    fun Route.getPostById() {
        get("/posts/{postId}") {
            val postId = call.parameters["postId"]?.toIntOrNull()
            if (postId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid or missing post ID")
                return@get
            }

            val rowsForPost = database.postQueries.getCompositePostById(postId).executeAsList()
            if (rowsForPost.isEmpty()) {
                call.respond(HttpStatusCode.NotFound, "Post not found.")
                return@get
            }

            val post = buildCompositePost(rowsForPost, postId)
            call.respond(post)
        }
    }

    fun Route.queryPostsComposite() {
        post("/posts/query") {
            try {

                val postIds = database.postQueries.selectPosts(8).executeAsList().map { it.id }
                if (postIds.isEmpty()) {
                    call.respond(emptyList<Post.Composite>())
                    return@post
                }

                val allRows = postIds.flatMap { database.postQueries.getCompositePostById(it).executeAsList() }

                val posts = allRows
                    .groupBy { it.post_id }
                    .map { (postId, rowsForPost) -> buildCompositePost(rowsForPost, postId) }

                call.respond(posts)

            } catch (error: Throwable) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    error.message ?: "An internal error occurred."
                )
            }
        }
    }

    fun Route.getPosts() {
        get("/posts") {
            try {
                val postIds = database.postQueries.selectPosts(8).executeAsList().map { it.id }
                if (postIds.isEmpty()) {
                    call.respond(emptyList<Post.Composite>())
                    return@get
                }

                val allRows = postIds.flatMap { database.postQueries.getCompositePostById(it).executeAsList() }

                val posts = allRows
                    .groupBy { it.post_id }
                    .map { (postId, rowsForPost) -> buildCompositePost(rowsForPost, postId) }

                call.respond(posts)
            } catch (error: Throwable) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    error.message ?: "An internal error occurred."
                )
            }
        }
    }

    private fun buildCompositePost(rowsForPost: List<GetCompositePostById>, postId: Int): Post.Composite {
        val entity = rowsForPost.first()

        val postKey = Post.Key(entity.post_id)
        val postProperties = Post.Properties(
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
            coverURL = entity.post_cover_url,
        )
        val postNode = Post.Node(postKey, postProperties)

        val creatorKey = Creator.Key(
            entity.creator_id ?: error("Missing creator ID.")
        )

        val creatorProperties = Creator.Properties(
            username = entity.creator_username ?: error("Missing creator username."),
            fullName = entity.creator_full_name,
            profilePicURL = entity.creator_profile_pic_url,
            isVerified = entity.creator_is_verified ?: false,
            bio = entity.creator_bio,
            platform = entity.creator_platform ?: error("Missing creator platform.")
        )

        val creatorNode = Creator.Node(creatorKey, creatorProperties)

        return Post.Composite(
            node = postNode,
            edges = Post.Edges(
                creator = creatorNode,
                hashtags = emptyList(),
                mentions = emptyList(),
                media = emptyList()
            )
        )



//        val hashtags = rowsForPost
//            .mapNotNull { row ->
//                row.hashtag_id?.let { id ->
//                    row.hashtag_name?.let { name -> Hashtag(id, name) }
//                }
//            }
//            .distinctBy { it.id }
//
//        val mentions = rowsForPost
//            .mapNotNull { row ->
//                row.mention_id?.let { id ->
//                    row.mention_platform?.let { platform ->
//                        row.mention_mentioned_username?.let { username ->
//                            Mention(id, postId, username, platform)
//                        }
//                    }
//                }
//            }
//            .distinctBy { it.id }
//
//        val media = rowsForPost
//            .mapNotNull { row ->
//                row.media_id?.let { id ->
//                    row.media_media_url?.let { url ->
//                        row.media_media_type?.let { type ->
//                            Media(
//                                id,
//                                postId,
//                                url,
//                                type,
//                                row.media_height,
//                                row.media_width,
//                                row.media_duration,
//                                row.media_alt_text,
//                                row.media_media_format
//                            )
//                        }
//                    }
//                }
//            }
//            .distinctBy { it.id }
//
//        return CompositePost(post, creator, hashtags, mentions, media)
    }
}