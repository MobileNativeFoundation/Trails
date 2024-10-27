package org.mobilenativefoundation.trails.backend.models


import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Creator(
    val id: Int,
    val username: String,
    val fullName: String?,
    val profilePicUrl: String?,
    val isVerified: Boolean,
    val bio: String?,
    val platform: Platform
)


@Serializable
data class CompositeCreator(
    val creator: Creator,
    val posts: List<Post>
)

@Serializable
data class Post(
    val id: Int,
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
data class CompositePost(
    val post: Post,
    val creator: Creator,
    val hashtags: List<Hashtag>,
    val mentions: List<Mention>,
    val media: List<Media>
)

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