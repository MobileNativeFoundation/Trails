package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store

import kotlinx.coroutines.CoroutineDispatcher
import org.mobilenativefoundation.store.core5.ExperimentalStoreApi
import org.mobilenativefoundation.store.store5.Converter
import org.mobilenativefoundation.store.store5.MutableStore
import org.mobilenativefoundation.store.store5.MutableStoreBuilder
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper.PostBookkeeperFactory
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database.PostSourceOfTruthFactory
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database.PostSourceOfTruthReader
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database.PostSourceOfTruthWriter
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.fetcher.PostFetcherFactory
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.models.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.updater.PostUpdaterFactory
import org.mobilenativefoundation.trails.xplat.lib.rest.api.post.PostOperations


typealias PostOperation = Operation<Post.Key, Post.Properties, Post.Edges, Post.Node>

@OptIn(ExperimentalStoreApi::class)
typealias PostStore = MutableStore<PostOperation, PostOutput>

@OptIn(ExperimentalStoreApi::class)
class PostStoreFactory(
    client: PostOperations,
    trailsDatabase: TrailsDatabase,
    coroutineDispatcher: CoroutineDispatcher
) {

    private val sourceOfTruthReader = PostSourceOfTruthReader(trailsDatabase, coroutineDispatcher)
    private val sourceOfTruthWriter = PostSourceOfTruthWriter(trailsDatabase, coroutineDispatcher)
    private val sourceOfTruthFactory = PostSourceOfTruthFactory(sourceOfTruthReader, sourceOfTruthWriter)
    private val fetcherFactory = PostFetcherFactory(client, trailsDatabase)
    private val updaterFactory = PostUpdaterFactory(client)
    private val bookkeeperFactory = PostBookkeeperFactory(trailsDatabase)

    fun create(): PostStore {
        return MutableStoreBuilder.from(
            fetcher = fetcherFactory.create(),
            sourceOfTruth = sourceOfTruthFactory.create(),
            converter = createConverter(),
        )
            .build(
                updater = updaterFactory.create(),
                bookkeeper = bookkeeperFactory.create()
            )
    }

    private fun createConverter(): Converter<PostOutput, PostOutput, PostOutput> =
        Converter.Builder<PostOutput, PostOutput, PostOutput>()
            .fromNetworkToLocal { it }
            .fromOutputToLocal { it }
            .build()
}