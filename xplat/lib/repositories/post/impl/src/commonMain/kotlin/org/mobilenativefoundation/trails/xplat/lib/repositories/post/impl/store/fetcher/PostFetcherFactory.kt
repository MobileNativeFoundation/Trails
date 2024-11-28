package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.fetcher

import kotlinx.coroutines.flow.flow
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostOperation
import org.mobilenativefoundation.trails.xplat.lib.store.Factory


class PostFetcherFactory(
    private val postFetcherServices: PostFetcherServices,
) : Factory<Fetcher<PostOperation, PostOutput>> {
    override fun create(): Fetcher<PostOperation, PostOutput> = Fetcher.ofFlow { operation ->
        // We never invoke fetcher after local writes
        // We do invoke updater on reads if conflicts might exist

        require(operation is Operation.Query)

        flow {
            when (operation) {
                is Operation.Query.FindMany -> postFetcherServices.findAndEmitMany(operation) { emit(it) }
                is Operation.Query.FindOne -> postFetcherServices.findAndEmitOne(operation) { emit(it) }
                is Operation.Query.FindOneComposite -> postFetcherServices.findAndEmitOneComposite(operation) {
                    emit(
                        it
                    )
                }

                is Operation.Query.ObserveMany -> postFetcherServices.observeManyAndEmitUpdates(operation) { emit(it) }
                is Operation.Query.ObserveOne -> postFetcherServices.observeOneAndEmitUpdates(operation) { emit(it) }
                is Operation.Query.ObserveOneComposite -> postFetcherServices.observeOneCompositeAndEmitUpdates(
                    operation
                ) { emit(it) }

                is Operation.Query.QueryOne -> postFetcherServices.queryAndEmitOne(operation) { emit(it) }
                is Operation.Query.FindAll -> postFetcherServices.findAndEmitAll(operation) { emit(it) }
                is Operation.Query.QueryMany -> postFetcherServices.queryAndEmitMany(operation) { emit(it) }
                is Operation.Query.QueryManyComposite -> postFetcherServices.queryAndEmitManyComposite(operation) {
                    emit(
                        it
                    )
                }
            }
        }
    }
}
