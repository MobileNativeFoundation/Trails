package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions

import kotlinx.datetime.LocalDateTime
import org.mobilenativefoundation.trails.xplat.lib.db.GetCompositePostById
import org.mobilenativefoundation.trails.xplat.lib.models.post.*

object GetCompositePostByIdExtensions {

    fun GetCompositePostById.asPost(): Post.Node {
        val key = Post.Key(this.post_id.toInt())
        val properties = Post.Properties(
            creatorId = this.post_creator_id.toInt(),
            caption = this.post_caption,
            platform = this.post_platform,
            createdAt = LocalDateTime.parse(this.post_created_at),
            likesCount = this.post_likes_count,
            commentsCount = this.post_comments_count,
            sharesCount = this.post_shares_count,
            viewsCount = this.post_views_count,
            isSponsored = this.post_is_sponsored == 1L,
            locationName = this.post_location_name,
            coverURL = this.post_cover_url,
        )
        return Post.Node(key, properties)
    }

    fun GetCompositePostById.asCreator(): Creator.Node {
        val key = Creator.Key(this.creator_id?.toInt() ?: error("Missing creator ID."))
        val properties = Creator.Properties(
            username = this.creator_username ?: error("Missing creator username."),
            fullName = this.creator_full_name,
            profilePicURL = this.creator_profile_pic_url,
            isVerified = this.creator_is_verified == 1L,
            bio = this.creator_bio,
            platform = this.creator_platform ?: error("Missing creator platform.")
        )

        return Creator.Node(
            key, properties
        )
    }

    fun List<GetCompositePostById>.extractHashtags(): List<Hashtag.Node> {
        return this.mapNotNull { row ->
            val id = row.hashtag_id
            val name = row.hashtag_name
            if (id != null && name != null) {
                Hashtag.Node(key = Hashtag.Key(id.toInt()), properties = Hashtag.Properties(name = name))
            } else {
                null
            }
        }
    }

    fun List<GetCompositePostById>.extractMentions(postId: Long): List<Mention.Node> {
        return this.mapNotNull { row ->
            val id = row.mention_id
            val platform = row.mention_platform
            val mentionedUsername = row.mention_mentioned_username
            if (id != null && platform != null && mentionedUsername != null) {
                Mention.Node(
                    key = Mention.Key(id = id.toInt()),
                    properties = Mention.Properties(
                        postId = postId.toInt(),
                        mentionedUsername = mentionedUsername,
                        platform = platform
                    )
                )
            } else {
                null
            }
        }
    }

    fun List<GetCompositePostById>.extractMedia(): List<Media.Node> {
        return this.mapNotNull { row ->
            val id = row.media_id
            val mediaURL = row.media_media_url
            val mediaType = row.media_media_type
            val height = row.media_height
            val width = row.media_width
            val duration = row.media_duration
            val altText = row.media_alt_text
            val mediaFormat = row.media_media_format
            if (id != null && mediaURL != null && mediaType != null) {
                Media.Node(
                    key = Media.Key(id = id.toInt()),
                    properties = Media.Properties(
                        mediaURL = mediaURL,
                        mediaType = mediaType,
                        mediaFormat = mediaFormat,
                        duration = duration?.toInt(),
                        altText = altText,
                        height = height?.toInt(),
                        width = width?.toInt(),
                    )
                )
            } else {
                null
            }
        }
    }

}