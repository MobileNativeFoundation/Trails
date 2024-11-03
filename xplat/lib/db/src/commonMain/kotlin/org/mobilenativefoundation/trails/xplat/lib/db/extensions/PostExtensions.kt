package org.mobilenativefoundation.trails.xplat.lib.db.extensions

import org.mobilenativefoundation.trails.xplat.lib.db.*
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post

object PostExtensions {

    fun Post.Composite.toPostEntity(): PostEntity {
        val id = this.node.key.id
        val properties = this.node.properties

        return PostEntity(
            id = id.toLong(),
            creator_id = this.edges.creator.key.id.toLong(),
            caption = properties.caption,
            created_at = properties.createdAt.toString(),
            likes_count = properties.likesCount.toLong(),
            comments_count = properties.commentsCount.toLong(),
            shares_count = properties.sharesCount.toLong(),
            views_count = properties.viewsCount.toLong(),
            is_sponsored = if (properties.isSponsored) 1 else 0,
            cover_url = properties.coverURL,
            platform = properties.platform,
            location_name = properties.locationName,
        )
    }

    fun Post.Composite.toCreatorEntity(): CreatorEntity {
        val creator = this.edges.creator

        return CreatorEntity(
            id = creator.key.id.toLong(),
            username = creator.properties.username,
            full_name = creator.properties.fullName,
            profile_pic_url = creator.properties.profilePicURL,
            is_verified = if (creator.properties.isVerified) 1 else 0,
            bio = creator.properties.bio,
            platform = creator.properties.platform,
        )
    }

    fun Post.Composite.toHashtagEntities(): List<HashtagEntity> {

        return this.edges.hashtags.map {
            HashtagEntity(id = it.key.id.toLong(), name = it.properties.name)
        }
    }

    fun Post.Composite.toMentionEntities(): List<MentionEntity> {
        return this.edges.mentions.map {
            MentionEntity(
                id = it.key.id.toLong(),
                mentioned_username = it.properties.mentionedUsername,
                platform = it.properties.platform,
                post_id = it.properties.postId.toLong()
            )
        }
    }


    fun Post.Composite.toMediaEntities(): List<MediaEntity> {
        val postId = this.node.key.id
        return this.edges.media.map {
            MediaEntity(
                id = it.key.id.toLong(),
                post_id = postId.toLong(),
                media_url = it.properties.mediaURL,
                media_type = it.properties.mediaType,
                height = it.properties.height?.toLong(),
                width = it.properties.width?.toLong(),
                duration = it.properties.duration?.toLong(),
                alt_text = it.properties.altText,
                media_format = it.properties.mediaFormat,
            )
        }
    }
}