package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.store.core5.ExperimentalStoreApi
import org.mobilenativefoundation.trails.xplat.lib.coroutines.TrailsDispatchers
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.api.PostComponent
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.api.PostRepository
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostStore
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostStoreFactory
import org.mobilenativefoundation.trails.xplat.lib.rest.api.TrailsClientComponent

@OptIn(ExperimentalStoreApi::class)
@Component
abstract class RealPostComponent(
    private val database: TrailsDatabase,
    @Component val trailsClientComponent: TrailsClientComponent
) : PostComponent {


    @Provides
    fun providePostStore(): PostStore {
        return PostStoreFactory(
            client = trailsClientComponent.trailsClient,
            trailsDatabase = database,
            coroutineDispatcher = TrailsDispatchers.io
        ).create()
    }

    @Provides
    fun bindPostRepository(impl: RealPostRepository): PostRepository = impl

    companion object

}