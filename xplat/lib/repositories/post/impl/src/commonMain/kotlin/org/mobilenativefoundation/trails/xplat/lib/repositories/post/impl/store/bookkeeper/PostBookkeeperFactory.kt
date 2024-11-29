package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper

import org.mobilenativefoundation.store.store5.Bookkeeper
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostOperation
import org.mobilenativefoundation.trails.xplat.lib.store.Factory

class PostBookkeeperFactory(
    private val bookkeepingReader: PostBookkeepingReader,
    private val bookkeepingWriter: PostBookkeepingWriter,
    private val bookkeepingRemover: PostBookkeepingRemover
) : Factory<Bookkeeper<PostOperation>> {

    override fun create(): Bookkeeper<PostOperation> =
        Bookkeeper.by(
            getLastFailedSync = { operation ->
                // We only check with the bookkeeper on reads
                require(operation is Operation.Query)

                handleGetLastFailedSync(operation)
            },
            setLastFailedSync = { operation, timestamp ->
                // We only set failed syncs on writes
                require(operation is Operation.Mutation)
                handleSetLastFailedSync(operation, timestamp)
            },
            clear = { handleClear(it) },
            clearAll = { handleClearAll() }
        )

    private fun handleGetLastFailedSync(operation: Operation.Query<Post.Key, Post.Properties, Post.Edges, Post.Node>): Long? {
        return when (operation) {
            is Operation.Query.FindAll -> bookkeepingReader.findLastFailedUpdate()

            is Operation.Query.FindMany -> bookkeepingReader.findLastFailedUpdate(operation.keys)

            is Operation.Query.FindOne -> bookkeepingReader.findLastFailedUpdate(operation.key)

            is Operation.Query.FindOneComposite -> TODO()
            is Operation.Query.ObserveMany -> {
                // Updates to many nodes is not supported
                // So we can just return null
                null
            }

            is Operation.Query.ObserveOne -> {
                // TODO(): Support observing
                throw UnsupportedOperationException()
            }

            is Operation.Query.ObserveOneComposite -> {
                // TODO(): Support observing
                throw UnsupportedOperationException()
            }

            is Operation.Query.QueryMany -> {
                // Updates to many nodes is not supported
                // So we can just return null
                null
            }

            is Operation.Query.QueryOne -> {
                // TODO(): Support querying
                throw UnsupportedOperationException()
            }

            is Operation.Query.QueryManyComposite -> {
                // Updates to many composites is not supported
                // So we can just return null
                null
            }
        }
    }

    private fun handleSetLastFailedSync(
        operation: Operation.Mutation<Post.Key, Post.Properties, Post.Edges, Post.Node>,
        timestamp: Long
    ): Boolean {
        return when (operation) {
            is Operation.Mutation.Create.InsertOne -> {
                // TODO(): Support creating posts on the client
                throw UnsupportedOperationException()
            }

            Operation.Mutation.Delete.DeleteAll -> {
                throw UnsupportedOperationException()
            }

            is Operation.Mutation.Delete.DeleteMany -> {
                bookkeepingWriter.recordFailedDeletes(operation.keys, timestamp)
            }

            is Operation.Mutation.Delete.DeleteOne -> {
                bookkeepingWriter.recordFailedDelete(operation.key, timestamp)
            }

            is Operation.Mutation.Update.ReplaceOne -> {
                // TODO(): Support replace operations
                throw UnsupportedOperationException()
            }

            is Operation.Mutation.Update.UpdateOne -> {
                bookkeepingWriter.recordFailedUpdate(operation.node.key, timestamp)
            }

            is Operation.Mutation.Upsert.UpsertOne -> {
                // TODO(): Support creating posts on the client
                throw UnsupportedOperationException()
            }
        }
    }

    private fun handleClear(
        operation: PostOperation
    ): Boolean =
        when (operation) {
            is Operation.Mutation.Create.InsertOne -> {
                // Creating posts from the client is not supported
                false
            }

            is Operation.Mutation.Delete.DeleteAll -> {
                bookkeepingRemover.removeAllFailedDeletes()
            }

            is Operation.Mutation.Delete.DeleteMany -> {
                bookkeepingRemover.removeFailedDeletes(operation.keys)
            }

            is Operation.Mutation.Delete.DeleteOne -> {
                bookkeepingRemover.removeFailedDelete(operation.key)

            }

            is Operation.Mutation.Update.ReplaceOne -> {
                // Replacing a post is not supported
                false
            }

            is Operation.Mutation.Update.UpdateOne -> {
                bookkeepingRemover.removeFailedUpdate(operation.node.key)
            }

            is Operation.Mutation.Upsert.UpsertOne -> {
                // Upserting a post from the client is not supported
                false
            }

            is Operation.Query.FindAll -> {
                val updatesRemoved = bookkeepingRemover.removeAllFailedUpdates()
                val deletesRemoved = bookkeepingRemover.removeAllFailedDeletes()
                updatesRemoved && deletesRemoved
            }

            is Operation.Query.FindMany -> {
                removeFailedSyncs(operation.keys)
            }

            is Operation.Query.FindOne -> {
                removeFailedSyncs(operation.key)
            }

            is Operation.Query.FindOneComposite -> {
                removeFailedSyncs(operation.key)
            }

            is Operation.Query.ObserveMany -> {
                removeFailedSyncs(operation.keys)
            }

            is Operation.Query.ObserveOne -> {
                removeFailedSyncs(operation.key)
            }

            is Operation.Query.ObserveOneComposite -> {
                removeFailedSyncs(operation.key)
            }

            is Operation.Query.QueryMany -> {
                // Updating nodes based on a query is not supported
                false
            }

            is Operation.Query.QueryManyComposite -> {
                // Updating composites based on a query is not supported
                false
            }

            is Operation.Query.QueryOne -> {
                // Updating a node based on a query is not supported
                false
            }
        }

    private fun removeFailedSyncs(key: Post.Key): Boolean {
        val updatesRemoved = bookkeepingRemover.removeFailedUpdate(key)
        val deletesRemoved = bookkeepingRemover.removeFailedDelete(key)
        return updatesRemoved && deletesRemoved
    }

    private fun removeFailedSyncs(keys: List<Post.Key>): Boolean {
        val updatesRemoved = bookkeepingRemover.removeFailedUpdates(keys)
        val deletesRemoved = bookkeepingRemover.removeFailedDeletes(keys)
        return updatesRemoved && deletesRemoved
    }

    private fun handleClearAll(): Boolean {
        val updatesRemoved = bookkeepingRemover.removeAllFailedUpdates()
        val deletesRemoved = bookkeepingRemover.removeAllFailedDeletes()
        return updatesRemoved && deletesRemoved
    }
}