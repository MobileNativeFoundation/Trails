package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions

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
}