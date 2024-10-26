package org.mobilenativefoundation.trails.xplat.lib.market.post.api

import org.mobilenativefoundation.trails.xplat.lib.models.post.PopulatedPost

interface PostRepository {
    suspend fun getPosts(): List<PopulatedPost>
}