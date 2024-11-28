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
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database.*
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.fetcher.PostFetcherServices
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.fetcher.RealPostFetcherServices
import org.mobilenativefoundation.trails.xplat.lib.rest.api.TrailsClientComponent

@OptIn(ExperimentalStoreApi::class)
@Component
abstract class RealPostComponent(
    private val database: TrailsDatabase,
    @Component val trailsClientComponent: TrailsClientComponent
) : PostComponent {

    @Provides
    fun providePostDAO(): PostDAO {
        return RealPostDAO(database)
    }

    @Provides
    fun providePostFetcherServices(postDAO: PostDAO): PostFetcherServices {
        return RealPostFetcherServices(
            trailsClientComponent.trailsClient,
            postDAO
        )
    }

    @Provides
    fun providePostPredicateEvaluator(): PostPredicateEvaluator {
        return RealPostPredicateEvaluator()
    }

    @Provides
    fun providePostComparer(): PostComparer {
        return RealPostComparer()
    }

    @Provides
    fun providePostStore(
        postFetcherServices: PostFetcherServices,
        postDAO: PostDAO,
        postPredicateEvaluator: PostPredicateEvaluator,
        comparer: PostComparer
    ): PostStore {
        return PostStoreFactory(
            client = trailsClientComponent.trailsClient,
            trailsDatabase = database,
            coroutineDispatcher = TrailsDispatchers.io,
            postDAO = postDAO,
            postFetcherServices = postFetcherServices,
            predicateEvaluator = postPredicateEvaluator,
            comparer = comparer

        ).create()
    }

    @Provides
    fun bindPostRepository(impl: RealPostRepository): PostRepository = impl

    companion object

}