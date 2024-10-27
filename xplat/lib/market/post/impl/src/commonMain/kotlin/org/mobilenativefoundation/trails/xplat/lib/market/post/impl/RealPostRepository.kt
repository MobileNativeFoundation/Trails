package org.mobilenativefoundation.trails.xplat.lib.market.post.impl

import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.store.core5.ExperimentalStoreApi
import org.mobilenativefoundation.store.store5.StoreWriteRequest
import org.mobilenativefoundation.store.store5.StoreWriteResponse
import org.mobilenativefoundation.store.store5.impl.extensions.fresh
import org.mobilenativefoundation.store.store5.impl.extensions.get
import org.mobilenativefoundation.trails.xplat.lib.market.post.api.PostRepository
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.store.post.PostStore
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.store.posts.PostsStore
import org.mobilenativefoundation.trails.xplat.lib.models.post.CompositePost
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.PostsQuery

@OptIn(ExperimentalStoreApi::class)
@Inject
class RealPostRepository(
    private val postsStore: PostsStore,
    private val postStore: PostStore
) : PostRepository {
    override suspend fun getPosts(query: PostsQuery): List<CompositePost> {
        val postIds = postsStore.fresh(query)
        return postIds.map {
            postStore.get<Int, CompositePost, Boolean>(it)
        }
    }

    override suspend fun updatePost(
        postId: Int,
        likesCount: Long?,
        commentsCount: Long?,
        sharesCount: Long?,
        viewsCount: Long?
    ): CompositePost {
        val prevCompositePost = postStore.get<Int, CompositePost, Boolean>(postId)
        val prevPost = prevCompositePost.post
        val nextPost = prevPost.copy(
            likesCount = likesCount ?: prevPost.likesCount,
            commentsCount = commentsCount ?: prevPost.commentsCount,
            sharesCount = sharesCount ?: prevPost.sharesCount,
            viewsCount = viewsCount ?: prevPost.viewsCount
        )
        val nextCompositePost = prevCompositePost.copy(post = nextPost)

        val writeRequest = StoreWriteRequest.of<Int, CompositePost, Boolean>(
            key = postId,
            value = nextCompositePost
        )
        return when (postStore.write(writeRequest)) {
            is StoreWriteResponse.Error -> prevCompositePost
            is StoreWriteResponse.Success -> nextCompositePost
        }
    }
}