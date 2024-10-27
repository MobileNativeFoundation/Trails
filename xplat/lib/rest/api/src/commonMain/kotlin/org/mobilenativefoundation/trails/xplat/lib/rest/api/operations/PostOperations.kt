package org.mobilenativefoundation.trails.xplat.lib.rest.api.operations

import org.mobilenativefoundation.trails.backend.models.CompositePost
import org.mobilenativefoundation.trails.backend.models.Post

interface PostOperations {
    suspend fun getPosts(query: PostsQuery): List<CompositePost>
    suspend fun getPost(id: Int): CompositePost?
    suspend fun updatePost(post: Post): Boolean
}