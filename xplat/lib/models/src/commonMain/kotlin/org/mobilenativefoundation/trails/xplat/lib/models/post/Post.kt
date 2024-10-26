package org.mobilenativefoundation.trails.xplat.lib.models.post

import kotlinx.datetime.LocalDateTime

enum class Platform {
    TikTok,
    Instagram
}

data class Post(
    val id: Int,
    val creatorId: Int,
    val caption: String?,
    val createdAt: LocalDateTime,
    val likesCount: Long,
    val commentsCount: Long,
    val sharesCount: Long,
    val viewsCount: Long,
    val isSponsored: Boolean,
    val coverURL: String,
    val platform: Platform,
    val locationName: String?
)


data class PopulatedPost(
    val post: Post,
    val creator: Creator,
    val hashtags: List<Hashtag>,
    val mentions: List<Mention>,
    val media: List<Media>
)


data class Hashtag(
    val id: Int,
    val name: String
)


data class Mention(
    val id: Int,
    val postId: Int,
    val mentionedUsername: String,
    val platform: Platform
)