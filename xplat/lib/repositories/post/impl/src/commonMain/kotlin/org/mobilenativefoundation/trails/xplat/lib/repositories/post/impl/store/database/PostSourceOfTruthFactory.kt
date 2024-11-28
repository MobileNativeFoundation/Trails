package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostOperation
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput

class PostSourceOfTruthFactory(
    private val reader: PostSourceOfTruthReader,
    private val writer: PostSourceOfTruthWriter
) {

    fun create(): SourceOfTruth<PostOperation, PostOutput, PostOutput> =
        SourceOfTruth.of(
            reader = { operation ->

                val mutableSharedFlow = MutableSharedFlow<PostOutput?>(
                    replay = 8,
                    extraBufferCapacity = 20,
                    onBufferOverflow = BufferOverflow.DROP_OLDEST
                )

                // We only invoke the SOT on reads
                require(operation is Operation.Query)

                handleRead(operation) {
                    mutableSharedFlow.emit(it)
                }

                mutableSharedFlow.asSharedFlow()
            },
            writer = { operation, output ->
                writer.handleWrite(operation, output)
            },
            delete = { operation ->
                TODO()
            },
            deleteAll = {
                TODO()
            }
        )

    private fun handleRead(
        operation: Operation.Query<Post.Key, Post.Properties, Post.Edges, Post.Node>,
        emit: suspend (PostOutput?) -> Unit
    ) {

        when (operation) {
            is Operation.Query.FindAll -> TODO()
            is Operation.Query.FindMany -> TODO()
            is Operation.Query.FindOne -> reader.findOne(operation, emit)
            is Operation.Query.FindOneComposite -> TODO()
            is Operation.Query.ObserveMany -> TODO()
            is Operation.Query.ObserveOne -> TODO()
            is Operation.Query.ObserveOneComposite -> TODO()
            is Operation.Query.QueryMany -> TODO()
            is Operation.Query.QueryOne -> TODO()
            is Operation.Query.QueryManyComposite -> reader.queryManyComposite(operation.query, emit)
        }
    }
}