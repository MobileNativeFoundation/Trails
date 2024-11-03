package org.mobilenativefoundation.trails.backend.models


import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable


object Creator {

    @Serializable
    data class Key(val id: Int)

    @Serializable
    data class Node(
        val username: String,
        val fullName: String?,
        val profilePicUrl: String?,
        val isVerified: Boolean,
        val bio: String?,
        val platform: Platform
    )

    @Serializable
    data class Model(val key: Key, val node: Node)

    @Serializable
    data class Edges(
        val posts: List<Post.Model>
    )

    @Serializable
    data class Composite(
        val model: Model,
        val edges: Edges
    )
}


sealed class Post {
    @Serializable
    data class Node(
        val creatorId: Int,
        val caption: String?,
        val platform: Platform,
        val createdAt: LocalDateTime,
        val likesCount: Long,
        val commentsCount: Long,
        val sharesCount: Long,
        val viewsCount: Long,
        val isSponsored: Boolean,
        val locationName: String?,
        val coverUrl: String
    )

    @Serializable
    data class Key(val id: Int)

    @Serializable
    data class Model(val key: Key, val node: Node): Post()

    @Serializable
    data class Edges(
        val creator: Creator.Model,
        val hashtags: List<Hashtag>,
        val mentions: List<Mention>,
        val media: List<Media>
    )

    @Serializable
    data class Composite(
        val model: Model,
        val edges: Edges
    ): Post()
}


@Serializable
data class Media(
    val id: Int,
    val postId: Int,
    val mediaUrl: String,
    val mediaType: String,
    val height: Int?,
    val width: Int?,
    val duration: Int?,
    val altText: String?,
    val mediaFormat: String?
)

@Serializable
data class CompositeMedia(
    val media: Media
)

@Serializable
data class Comment(
    val id: Int,
    val postId: Int,
    val creatorId: Int,
    val content: String,
    val createdAt: Instant,
    val likesCount: Int,
    val parentCommentId: Int?
)

@Serializable
data class CompositeComment(
    val comment: Comment,
    val post: Post,
    val creator: Creator,
    val parentComment: Comment
)

@Serializable
data class Hashtag(
    val id: Int,
    val name: String
)

@Serializable
data class PostHashtag(
    val postId: Int,
    val hashtagId: Int
)

@Serializable
data class Mention(
    val id: Int,
    val postId: Int,
    val mentionedUsername: String,
    val platform: Platform
)

@Serializable
data class CompositeMention(
    val mention: Mention,
    val post: Post,
    val creator: Creator
)