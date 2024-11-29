package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl

import kotlinx.coroutines.Dispatchers
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.store.core5.ExperimentalStoreApi
import org.mobilenativefoundation.store.store5.Bookkeeper
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.Updater
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostWriteResponse
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.api.PostComponent
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.api.PostRepository
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostOperation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostStore
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostStoreFactory
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper.*
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.db.PostBookkeepingDAO
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.db.PostDAO
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.db.RealPostBookkeepingDAO
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.db.RealPostDAO
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.sot.*
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.fetcher.PostFetcherFactory
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.fetcher.PostFetcherServices
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.fetcher.RealPostFetcherServices
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.updater.PostUpdaterFactory
import org.mobilenativefoundation.trails.xplat.lib.rest.api.TrailsClientComponent
import org.mobilenativefoundation.trails.xplat.lib.store.Factory

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
    fun providePostFetcherFactory(
        postFetcherServices: PostFetcherServices
    ): Factory<Fetcher<PostOperation, PostOutput>> =
        PostFetcherFactory(postFetcherServices)

    @Provides
    fun provideComparator(): PostComparator = RealPostComparator()

    @Provides
    fun providePostSourceOfTruthReader(
        postDAO: PostDAO,
        predicateEvaluator: PostPredicateEvaluator,
        comparator: PostComparator
    ): PostSourceOfTruthReader =
        RealPostSourceOfTruthReader(
            postDAO,
            predicateEvaluator,
            comparator,
            Dispatchers.Default
        )

    @Provides
    fun providePostSourceOfTruthWriter(postDAO: PostDAO): PostSourceOfTruthWriter =
        RealPostSourceOfTruthWriter(postDAO)

    @Provides
    fun providePostSourceOfTruthFactory(
        reader: PostSourceOfTruthReader,
        writer: PostSourceOfTruthWriter
    ): Factory<SourceOfTruth<PostOperation, PostOutput, PostOutput>> =
        PostSourceOfTruthFactory(
            reader, writer
        )

    @Provides
    fun providePostUpdaterFactory(): Factory<Updater<PostOperation, PostOutput, PostWriteResponse>> =
        PostUpdaterFactory(trailsClientComponent.trailsClient)

    @Provides
    fun providePostBookkeepingDAO(): PostBookkeepingDAO =
        RealPostBookkeepingDAO(database)

    @Provides
    fun providePostBookkeepingReader(bookkeepingDAO: PostBookkeepingDAO): PostBookkeepingReader =
        RealPostBookkeepingReader(bookkeepingDAO)

    @Provides
    fun providePostBookkeepingWriter(bookkeepingDAO: PostBookkeepingDAO): PostBookkeepingWriter =
        RealPostBookkeepingWriter(bookkeepingDAO)

    @Provides
    fun providePostBookkeepingRemover(bookkeepingDAO: PostBookkeepingDAO): PostBookkeepingRemover =
        RealPostBookkeepingRemover(bookkeepingDAO)

    @Provides
    fun providePostBookkeeperFactory(
        bookkeepingReader: PostBookkeepingReader,
        bookkeepingWriter: PostBookkeepingWriter,
        bookkeepingRemover: PostBookkeepingRemover
    ): Factory<Bookkeeper<PostOperation>> = PostBookkeeperFactory(
        bookkeepingReader, bookkeepingWriter, bookkeepingRemover
    )

    @Provides
    fun providePostStore(
        fetcherFactory: Factory<Fetcher<PostOperation, PostOutput>>,
        sourceOfTruthFactory: Factory<SourceOfTruth<PostOperation, PostOutput, PostOutput>>,
        updaterFactory: Factory<Updater<PostOperation, PostOutput, PostWriteResponse>>,
        bookkeeperFactory: Factory<Bookkeeper<PostOperation>>
    ): PostStore {
        return PostStoreFactory(
            fetcherFactory,
            sourceOfTruthFactory,
            updaterFactory,
            bookkeeperFactory

        ).create()
    }

    @Provides
    fun bindPostRepository(impl: RealPostRepository): PostRepository = impl

    companion object

}