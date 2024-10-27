package org.mobilenativefoundation.trails.xplat.lib.market.post.api

import org.mobilenativefoundation.trails.xplat.lib.models.post.CompositePost
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.PostsQuery

interface PostRepository {
    suspend fun getPosts(query: PostsQuery): List<CompositePost>
    suspend fun updatePost(
        postId: Int,
        likesCount: Long? = null,
        commentsCount: Long? = null,
        sharesCount: Long? = null,
        viewsCount: Long? = null,
    ): CompositePost
}