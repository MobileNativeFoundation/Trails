package org.mobilenativefoundation.trails.xplat.lib.market.post.impl

import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.store.store5.impl.extensions.fresh
import org.mobilenativefoundation.store.store5.impl.extensions.get
import org.mobilenativefoundation.trails.xplat.lib.market.post.api.PostRepository
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.store.PostsStore
import org.mobilenativefoundation.trails.xplat.lib.models.post.PopulatedPost

@Inject
class RealPostRepository(
    private val store: PostsStore
) : PostRepository {
    override suspend fun getPosts(): List<PopulatedPost> {
        return store.fresh("skiing")
    }
}