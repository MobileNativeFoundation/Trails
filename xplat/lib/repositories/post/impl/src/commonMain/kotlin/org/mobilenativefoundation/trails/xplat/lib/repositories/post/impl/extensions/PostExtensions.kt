package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions

import kotlinx.datetime.LocalDateTime
import org.mobilenativefoundation.trails.xplat.lib.db.PostEntity
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.id


object PostExtensions {
    fun Post.Node.asEntity(): PostEntity {
        val id = this.key.id
        return with(this.properties) {
            PostEntity(
                id = id.toLong(),
                creator_id = this.creatorId.toLong(),
                caption = this.caption,
                created_at = createdAt.toString(),
                likes_count = this.likesCount,
                comments_count = this.commentsCount,
                shares_count = this.sharesCount,
                views_count = this.viewsCount,
                is_sponsored = if (this.isSponsored) 1 else 0,
                cover_url = this.coverURL,
                platform = this.platform,
                location_name = this.locationName
            )
        }
    }


    fun PostEntity.asNode(): Post.Node {
        return Post.Node(
            key = Post.Key(id = this.id.toInt()),
            properties = Post.Properties(
                creatorId = this.creator_id.toInt(),
                caption = this.caption,
                createdAt = LocalDateTime.parse(this.created_at),
                likesCount = this.likes_count.toLong(),
                commentsCount = this.comments_count.toLong(),
                sharesCount = this.shares_count.toLong(),
                viewsCount = this.views_count.toLong(),
                isSponsored = this.is_sponsored == 1L,
                coverURL = this.cover_url,
                platform = this.platform,
                locationName = this.location_name
            )
        )
    }

    fun Post.Node.asPostEntity(): PostEntity {
        return PostEntity(
            id = this.id.toLong(),
            creator_id = this.properties.creatorId.toLong(),
            caption = this.properties.caption,
            created_at = this.properties.createdAt.toString(),
            likes_count = this.properties.likesCount,
            comments_count = this.properties.commentsCount,
            shares_count = this.properties.sharesCount,
            views_count = this.properties.viewsCount,
            is_sponsored = if (this.properties.isSponsored) 1 else 0,
            cover_url = this.properties.coverURL,
            platform = this.properties.platform,
            location_name = this.properties.locationName
        )
    }
}