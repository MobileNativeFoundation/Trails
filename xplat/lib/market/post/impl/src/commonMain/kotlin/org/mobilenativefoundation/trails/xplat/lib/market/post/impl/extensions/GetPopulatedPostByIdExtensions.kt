package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions

import kotlinx.datetime.LocalDateTime
import org.mobilenativefoundation.trails.xplat.lib.db.GetPopulatedPostById
import org.mobilenativefoundation.trails.xplat.lib.models.post.Creator
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post

object GetPopulatedPostByIdExtensions {

    fun GetPopulatedPostById.asPost(): Post {
        return Post(
            id = this.post_id.toInt(),
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
    }

    fun GetPopulatedPostById.asCreator(): Creator {
        return Creator(
            id = this.creator_id?.toInt() ?: error("Missing creator ID"),
            username = this.creator_username ?: error("Missing creator username"),
            fullName = this.creator_full_name,
            profilePicURL = this.creator_profile_pic_url,
            isVerified = this.creator_is_verified == 1L,
            bio = this.creator_bio,
            platform = this.creator_platform ?: error("Missing creator platform")
        )
    }
}