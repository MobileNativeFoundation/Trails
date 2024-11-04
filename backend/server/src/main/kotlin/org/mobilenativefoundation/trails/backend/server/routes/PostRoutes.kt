package org.mobilenativefoundation.trails.backend.server.routes

import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.datetime.toKotlinLocalDateTime
import org.mobilenativefoundation.trails.backend.server.GetCompositePostById
import org.mobilenativefoundation.trails.backend.server.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.models.post.Creator
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.models.query.*
import kotlin.reflect.KProperty1
import kotlin.reflect.KProperty2
import kotlin.reflect.full.memberExtensionProperties
import kotlin.reflect.full.memberProperties

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


            val query = call.receive<Query.Many>()


            try {

                val entities = database.postQueries.selectAllPosts().executeAsList()

                if (entities.isEmpty()) {
                    call.respond(emptyList<Post.Composite>())
                    return@post
                }

                val nodes = entities.map { it.asNode() }.asSequence()
                    .filter { item -> query.predicate?.let { evaluatePredicate(it, item) } ?: true }
                    .sortedWith { a, b -> compareItems(a, b, query.order) }
                    .let { sequence ->
                        query.limit?.let { sequence.take(it) } ?: sequence
                    }.toList()

                val allRows =
                    nodes.map { it.id }.flatMap { database.postQueries.getCompositePostById(it).executeAsList() }

                val composites = allRows
                    .groupBy { it.post_id }
                    .map { (postId, rowsForPost) -> buildCompositePost(rowsForPost, postId) }

                call.respond(PostOutput.Collection(composites))

            } catch (error: Throwable) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    error.message ?: "An internal error occurred."
                )
            }
        }
    }

    private fun org.mobilenativefoundation.trails.backend.server.Post.asNode(): Post.Node {
        val key = Post.Key(this.id)
        val properties = Post.Properties(
            creatorId = this.creator_id,
            caption = this.caption,
            createdAt = this.created_at.toKotlinLocalDateTime(),
            likesCount = this.likes_count,
            commentsCount = this.comments_count,
            sharesCount = this.shares_count,
            viewsCount = this.views_count,
            isSponsored = is_sponsored,
            coverURL = cover_url,
            platform = platform,
            locationName = location_name
        )
        return Post.Node(key, properties)
    }


    private fun evaluatePredicate(predicate: Predicate, item: Post.Node): Boolean {
        return when (predicate) {
            is Predicate.Comparison.StringComparison -> {
                val propertyValue = getStringProperty(item, predicate.propertyName)
                val comparisonValue = predicate.value as String

                when (predicate.operator) {
                    ComparisonOperator.EQUALS -> propertyValue == comparisonValue
                    ComparisonOperator.NOT_EQUALS -> propertyValue != comparisonValue
                    ComparisonOperator.GREATER_THAN -> propertyValue > comparisonValue
                    ComparisonOperator.LESS_THAN -> propertyValue < comparisonValue
                    ComparisonOperator.GREATER_THAN_OR_EQUALS -> propertyValue >= comparisonValue
                    ComparisonOperator.LESS_THAN_OR_EQUALS -> propertyValue <= comparisonValue
                    ComparisonOperator.CONTAINS -> propertyValue.contains(comparisonValue)
                }
            }


            is Predicate.Logical -> {
                val evaluations = predicate.predicates.map { evaluatePredicate(it, item) }
                when (predicate.operator) {
                    LogicalOperator.AND -> evaluations.all { it }
                    LogicalOperator.OR -> evaluations.any { it }
                }
            }

            is Predicate.Comparison.BooleanComparison -> {
                val propertyValue = getBooleanProperty(item, predicate.propertyName)
                val comparisonValue = predicate.value as Boolean

                when (predicate.operator) {
                    ComparisonOperator.EQUALS -> propertyValue == comparisonValue
                    ComparisonOperator.NOT_EQUALS -> propertyValue != comparisonValue
                    ComparisonOperator.GREATER_THAN -> propertyValue > comparisonValue
                    ComparisonOperator.LESS_THAN -> propertyValue < comparisonValue
                    ComparisonOperator.GREATER_THAN_OR_EQUALS -> propertyValue >= comparisonValue
                    ComparisonOperator.LESS_THAN_OR_EQUALS -> propertyValue <= comparisonValue
                    ComparisonOperator.CONTAINS -> throw UnsupportedOperationException()
                }
            }

            is Predicate.Comparison.IntComparison -> {
                val propertyValue = getIntProperty(item, predicate.propertyName)
                val comparisonValue = predicate.value as Int

                when (predicate.operator) {
                    ComparisonOperator.EQUALS -> propertyValue == comparisonValue
                    ComparisonOperator.NOT_EQUALS -> propertyValue != comparisonValue
                    ComparisonOperator.GREATER_THAN -> propertyValue > comparisonValue
                    ComparisonOperator.LESS_THAN -> propertyValue < comparisonValue
                    ComparisonOperator.GREATER_THAN_OR_EQUALS -> propertyValue >= comparisonValue
                    ComparisonOperator.LESS_THAN_OR_EQUALS -> propertyValue <= comparisonValue
                    ComparisonOperator.CONTAINS -> throw UnsupportedOperationException()
                }
            }
        }
    }

    private fun compareItems(a: Post.Node, b: Post.Node, order: Order?): Int {
        return if (order != null) {

            return when (order.propertyType) {
                Type.STRING -> {
                    val valueA = getStringProperty(a, order.propertyName)
                    val valueB = getStringProperty(b, order.propertyName)
                    val comparison = valueA.compareTo(valueB)
                    if (order.direction == Direction.ASC) comparison else -comparison
                }

                Type.INT -> {
                    val valueA = getIntProperty(a, order.propertyName)
                    val valueB = getIntProperty(b, order.propertyName)
                    val comparison = valueA.compareTo(valueB)
                    if (order.direction == Direction.ASC) comparison else -comparison
                }
            }
        } else {
            0
        }
    }


    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getStringProperty(instance: T, propertyName: String): String {
        println("PROPERTIES = ${instance::class.memberProperties}")
        println("MEMBERS = ${instance::class.members}")
        println("MEMBER EXTENSION PROPS = ${instance::class.memberExtensionProperties}")
        val property =
            instance::class.memberProperties.firstOrNull { it.name == propertyName } as? KProperty1<T, String>
        val extensionProperty =
            instance::class.memberExtensionProperties.firstOrNull { it.name == propertyName } as? KProperty2<T, T, String>

        return property?.get(instance) ?: extensionProperty?.get(instance, instance)
        ?: error("Property name $propertyName not found.")
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getIntProperty(instance: T, propertyName: String): Int {
        println("PROPERTIES = ${instance::class.memberProperties}")
        println("MEMBERS = ${instance::class.members}")
        println("MEMBER EXTENSION PROPS = ${instance::class.memberExtensionProperties}")
        val property = instance::class.memberProperties.firstOrNull { it.name == propertyName } as? KProperty1<T, Int>
        val extensionProperty =
            instance::class.memberExtensionProperties.firstOrNull { it.name == propertyName } as? KProperty2<T, T, Int>

        return property?.get(instance) ?: extensionProperty?.get(instance, instance)
        ?: error("Property name $propertyName not found.")
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getBooleanProperty(instance: T, propertyName: String): Boolean {
        println("PROPERTIES = ${instance::class.memberProperties}")
        println("MEMBERS = ${instance::class.members}")
        println("MEMBER EXTENSION PROPS = ${instance::class.memberExtensionProperties}")
        val property =
            instance::class.memberProperties.firstOrNull { it.name == propertyName } as? KProperty1<T, Boolean>
        val extensionProperty =
            instance::class.memberExtensionProperties.firstOrNull { it.name == propertyName } as? KProperty2<T, T, Boolean>

        return property?.get(instance) ?: extensionProperty?.get(instance, instance)
        ?: error("Property name $propertyName not found.")
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getLongProperty(instance: T, propertyName: String): Long {
        println("PROPERTIES = ${instance::class.memberProperties}")
        println("MEMBERS = ${instance::class.members}")
        println("MEMBER EXTENSION PROPS = ${instance::class.memberExtensionProperties}")
        val property = instance::class.memberProperties.firstOrNull { it.name == propertyName } as? KProperty1<T, Long>
        val extensionProperty =
            instance::class.memberExtensionProperties.firstOrNull { it.name == propertyName } as? KProperty2<T, T, Long>

        return property?.get(instance) ?: extensionProperty?.get(instance, instance)
        ?: error("Property name $propertyName not found.")
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