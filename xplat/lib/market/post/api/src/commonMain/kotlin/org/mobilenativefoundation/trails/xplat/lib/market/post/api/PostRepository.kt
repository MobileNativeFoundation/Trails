package org.mobilenativefoundation.trails.xplat.lib.market.post.api

import org.mobilenativefoundation.trails.xplat.lib.models.post.CompositePost

interface PostRepository {
    suspend fun getPosts(): List<CompositePost>
}