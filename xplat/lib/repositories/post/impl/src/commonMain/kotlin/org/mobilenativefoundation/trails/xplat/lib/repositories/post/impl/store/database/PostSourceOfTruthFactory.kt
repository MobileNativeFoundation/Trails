package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.mobilenativefoundation.store.store5.SourceOfTruth
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

                reader.handleRead(operation) {
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
}

// SourceOfTruth.of(
//            reader = { operation ->
//
//                val mutableSharedFlow = MutableSharedFlow<Post?>(
//                    replay = 8,
//                    extraBufferCapacity = 20,
//                    onBufferOverflow = BufferOverflow.DROP_OLDEST
//                )
//
//                // We only invoke the SOT on reads
//                require(operation is Operation.Query)
//
//                when (operation) {
//                    is PostOperation.Query.FindOne -> {
//                        val id = operation.key.id
//                        coroutineScope.launch {
//                            mutableSharedFlow.emit(trailsDatabase.postQueries.assemblePostModel(id.toLong()))
//                        }
//                    }
//
//                    is PostOperation.Query.FindOneComposite -> {
//                        val id = operation.key.id
//                        coroutineScope.launch {
//                            mutableSharedFlow.emit(trailsDatabase.postQueries.assembleCompositePost(id.toLong()))
//                        }
//                    }
//
//                    is PostOperation.Query.ObserveOne -> {
//                        val id = operation.key.id
//                        coroutineScope.launch {
//                            trailsDatabase.postQueries.observePostModel(id.toLong()).collect {
//                                mutableSharedFlow.emit(it)
//                            }
//                        }
//                    }
//
//                    is PostOperation.Query.ObserveOneComposite -> {
//                        val id = operation.key.id
//                        coroutineScope.launch {
//                            trailsDatabase.postQueries.observeCompositePost(id.toLong()).collect {
//                                mutableSharedFlow.emit(it)
//                            }
//                        }
//                    }
//
//                    is PostOperation.Query.QueryOne -> TODO()
//                }
//
//                mutableSharedFlow.asSharedFlow()