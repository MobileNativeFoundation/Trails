package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store

import org.mobilenativefoundation.store.core5.ExperimentalStoreApi
import org.mobilenativefoundation.store.store5.*
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostWriteResponse
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.store.Factory


typealias PostOperation = Operation<Post.Key, Post.Properties, Post.Edges, Post.Node>

@OptIn(ExperimentalStoreApi::class)
typealias PostStore = MutableStore<PostOperation, PostOutput>

@OptIn(ExperimentalStoreApi::class)
class PostStoreFactory(
    private val fetcherFactory: Factory<Fetcher<PostOperation, PostOutput>>,
    private val sourceOfTruthFactory: Factory<SourceOfTruth<PostOperation, PostOutput, PostOutput>>,
    private val updaterFactory: Factory<Updater<PostOperation, PostOutput, PostWriteResponse>>,
    private val bookkeeperFactory: Factory<Bookkeeper<PostOperation>>
) : Factory<PostStore> {

    override fun create(): PostStore {
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