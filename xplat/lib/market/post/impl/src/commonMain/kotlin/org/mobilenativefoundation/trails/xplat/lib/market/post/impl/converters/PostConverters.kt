package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.converters

import org.mobilenativefoundation.trails.xplat.lib.models.post.*
import org.mobilenativefoundation.trails.backend.models.Creator as CreatorNetworkModel
import org.mobilenativefoundation.trails.backend.models.Platform as PlatformNetworkModel
import org.mobilenativefoundation.trails.backend.models.PopulatedPost as PopulatedPostNetworkModel
import org.mobilenativefoundation.trails.backend.models.Post as PostNetworkModel

object PostConverters {
    fun PopulatedPostNetworkModel.asPopulatedPost(): PopulatedPost {
        return PopulatedPost(
            post = this.post.asPost(),
            creator = this.creator.asCreator(),
            hashtags = listOf(),
            media = listOf(),
            mentions = listOf()
        )
    }

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

    fun PlatformNetworkModel.asPlatform(): Platform {
        return when (this) {
            PlatformNetworkModel.TikTok -> Platform.TikTok
            PlatformNetworkModel.Instagram -> Platform.Instagram
        }
    }

    fun CreatorNetworkModel.asCreator(): Creator {
        return Creator(
            id = this.id,
            username = this.username,
            fullName = fullName,
            profilePicURL = profilePicUrl,
            isVerified = isVerified,
            bio = bio,
            platform = platform.asPlatform()
        )
    }

}