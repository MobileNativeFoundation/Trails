package org.mobilenativefoundation.trails.xplat.lib.db.extensions

import org.mobilenativefoundation.trails.xplat.lib.db.*
import org.mobilenativefoundation.trails.xplat.lib.models.post.CompositePost

object PostExtensions {

    fun CompositePost.toPostEntity(): PostEntity {

        return PostEntity(
            id = this.post.id.toLong(),
            creator_id = this.creator.id.toLong(),
            caption = this.post.caption,
            created_at = this.post.createdAt.toString(),
            likes_count = this.post.likesCount.toLong(),
            comments_count = this.post.commentsCount.toLong(),
            shares_count = this.post.sharesCount.toLong(),
            views_count = this.post.viewsCount.toLong(),
            is_sponsored = if (this.post.isSponsored) 1 else 0,
            cover_url = this.post.coverURL,
            platform = this.post.platform,
            location_name = this.post.locationName,
        )
    }

    fun CompositePost.toCreatorEntity(): CreatorEntity {
        return CreatorEntity(
            id = this.creator.id.toLong(),
            username = creator.username,
            full_name = creator.fullName,
            profile_pic_url = creator.profilePicURL,
            is_verified = if (creator.isVerified) 1 else 0,
            bio = creator.bio,
            platform = creator.platform,
        )
    }

    fun CompositePost.toHashtagEntities(): List<HashtagEntity> {
        return this.hashtags.map {
            HashtagEntity(id = it.id.toLong(), name = it.name)
        }
    }

    fun CompositePost.toMentionEntities(): List<MentionEntity> {
        return this.mentions.map {
            MentionEntity(
                id = it.id.toLong(),
                mentioned_username = it.mentionedUsername,
                platform = it.platform,
                post_id = it.postId.toLong()
            )
        }
    }

    fun CompositePost.toMediaEntities(): List<MediaEntity> {
        return this.media.map {
            MediaEntity(
                id = it.id.toLong(),
                post_id = this.post.id.toLong(),
                media_url = it.mediaURL,
                media_type = it.mediaType,
                height = it.height?.toLong(),
                width = it.width?.toLong(),
                duration = it.duration?.toLong(),
                alt_text = it.altText,
                media_format = it.mediaFormat,
            )
        }
    }
}