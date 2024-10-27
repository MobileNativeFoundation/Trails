package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions

import kotlinx.datetime.LocalDateTime
import org.mobilenativefoundation.trails.xplat.lib.db.PostEntity
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PlatformExtensions.asNetworkModel
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PlatformExtensions.asPlatform
import org.mobilenativefoundation.trails.xplat.lib.models.post.*
import org.mobilenativefoundation.trails.backend.models.Post as PostNetworkModel


object PostExtensions {
    fun PostNetworkModel.asPost(): Post {
        return Post(
            id = this.id,
            creatorId = this.creatorId,
            caption = this.caption,
            createdAt = this.createdAt,
            likesCount = this.likesCount,
            commentsCount = this.commentsCount,
            sharesCount = this.sharesCount,
            viewsCount = this.viewsCount,
            isSponsored = this.isSponsored,
            coverURL = this.coverUrl,
            platform = this.platform.asPlatform(),
            locationName = this.locationName
        )
    }

    fun PostEntity.asPost(): Post {
        return Post(
            id = this.id.toInt(),
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
    }

    fun Post.asPostEntity(): PostEntity {
        return PostEntity(
            id = this.id.toLong(),
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

    fun Post.asNetworkModel(): PostNetworkModel {
        return PostNetworkModel(
            id = this.id,
            creatorId = this.creatorId,
            caption = this.caption,
            createdAt = createdAt,
            likesCount = this.likesCount,
            commentsCount = this.commentsCount,
            sharesCount = this.sharesCount,
            viewsCount = this.viewsCount,
            isSponsored = isSponsored,
            coverUrl = this.coverURL,
            platform = this.platform.asNetworkModel(),
            locationName = this.locationName
        )
    }
}