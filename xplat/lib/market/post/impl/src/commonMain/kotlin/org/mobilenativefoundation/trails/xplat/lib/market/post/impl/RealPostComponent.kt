package org.mobilenativefoundation.trails.xplat.lib.market.post.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.market.post.api.PostComponent
import org.mobilenativefoundation.trails.xplat.lib.market.post.api.PostRepository
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.store.PostsStore
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.store.PostsStoreFactory
import org.mobilenativefoundation.trails.xplat.lib.rest.api.TrailsClientComponent

@Component
abstract class RealPostComponent(
    private val database: TrailsDatabase,
    @Component val trailsClientComponent: TrailsClientComponent
) : PostComponent {

    @Provides
    fun providePostsStore(): PostsStore {
        return PostsStoreFactory(
            client = trailsClientComponent.trailsClient,
            trailsDatabase = database
        ).create()
    }

    @Provides
    fun bindPostRepository(impl: RealPostRepository): PostRepository = impl

    companion object

}