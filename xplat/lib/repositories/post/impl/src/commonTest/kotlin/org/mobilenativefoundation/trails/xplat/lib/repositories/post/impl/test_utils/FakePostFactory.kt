package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.test_utils

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.mobilenativefoundation.trails.xplat.lib.db.PostEntity
import org.mobilenativefoundation.trails.xplat.lib.models.post.Platform
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.test_utils.FakeCreatorFactory.createCreator

object FakePostFactory {
    fun createPost(id: Int) = Post.Node(
        key = Post.Key(id),
        properties = Post.Properties(
            creatorId = id,
            caption = "",
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            likesCount = 0,
            commentsCount = 0,
            sharesCount = 0,
            viewsCount = 0,
            isSponsored = false,
            coverURL = "",
            platform = Platform.Instagram,
            locationName = null,
        )
    )

    fun createCompositePost(id: Int) = Post.Composite(
        node = createPost(id),
        edges = Post.Edges(
            creator = createCreator(id),
            hashtags = emptyList(),
            mentions = emptyList(),
            media = emptyList()
        )
    )

    fun createPostEntity(id: Int) = PostEntity(
        id = id.toLong(),
        creator_id = id.toLong(),
        caption = "",
        created_at = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toString(),
        likes_count = 0,
        comments_count = 0,
        shares_count = 0,
        views_count = 0,
        is_sponsored = 0,
        cover_url = "",
        platform = Platform.Instagram,
        location_name = null
    )
}