package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper

import org.mobilenativefoundation.store.store5.Bookkeeper
import org.mobilenativefoundation.trails.xplat.lib.db.PostFailedDelete
import org.mobilenativefoundation.trails.xplat.lib.db.PostFailedUpdate
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostOperation

class PostBookkeeperFactory(
    private val trailsDatabase: TrailsDatabase
) {

    fun create(): Bookkeeper<PostOperation> =
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
            clear = { operation ->
                TODO()
            },
            clearAll = {
                TODO()
            }
        )

    private fun handleGetLastFailedSync(operation: Operation.Query<Post.Key, Post.Properties, Post.Edges, Post.Node>): Long? {
        return when (operation) {
            is Operation.Query.FindAll -> {
                val failedUpdates = trailsDatabase.postBookkeepingQueries.getFailedUpdates().executeAsList()
                val failedDeletes = trailsDatabase.postBookkeepingQueries.getFailedDeletes().executeAsList()

                return when {
                    failedUpdates.isEmpty() && failedDeletes.isEmpty() -> null
                    failedUpdates.isNotEmpty() -> failedUpdates.first().timestamp
                    else -> failedDeletes.first().timestamp
                }
            }

            is Operation.Query.FindMany -> {
                val ids = operation.keys.map { it.id.toLong() }

                val failedUpdates = trailsDatabase.postBookkeepingQueries.getManyFailedUpdates(ids).executeAsList()
                val failedDeletes = trailsDatabase.postBookkeepingQueries.getManyFailedDeletes(ids).executeAsList()

                return when {
                    failedUpdates.isEmpty() && failedDeletes.isEmpty() -> null
                    failedUpdates.isNotEmpty() -> failedUpdates.first().timestamp
                    else -> failedDeletes.first().timestamp
                }
            }

            is Operation.Query.FindOne -> {
                val failedUpdates = trailsDatabase.postBookkeepingQueries.getOneFailedUpdate(operation.key.id.toLong())
                    .executeAsOneOrNull()
                val failedDeletes = trailsDatabase.postBookkeepingQueries.getOneFailedDelete(operation.key.id.toLong())
                    .executeAsOneOrNull()

                return when {
                    failedUpdates == null && failedDeletes == null -> null
                    failedUpdates != null -> failedUpdates.timestamp
                    else -> failedDeletes?.timestamp
                }
            }

            is Operation.Query.FindOneComposite -> TODO()
            is Operation.Query.ObserveMany -> {
                // TODO(): Support observing
                throw UnsupportedOperationException()
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
                // TODO(): Support querying
                throw UnsupportedOperationException()
            }

            is Operation.Query.QueryOne -> {
                // TODO(): Support querying
                throw UnsupportedOperationException()
            }

            is Operation.Query.QueryManyComposite -> {
                // TODO(): Support querying
                throw UnsupportedOperationException()
            }
        }
    }

    private suspend fun handleSetLastFailedSync(
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
                operation.keys.forEach { key ->
                    insertOneDeleteFailed(key.id, timestamp)
                }
                true
            }

            is Operation.Mutation.Delete.DeleteOne -> {
                insertOneDeleteFailed(operation.key.id, timestamp)
                true
            }

            is Operation.Mutation.Update.ReplaceOne -> {
                // TODO(): Support replace operations
                throw UnsupportedOperationException()
            }

            is Operation.Mutation.Update.UpdateOne -> {
                trailsDatabase.postBookkeepingQueries.insertFailedUpdate(
                    PostFailedUpdate(
                        post_id = operation.node.key.id.toLong(),
                        timestamp = timestamp.toLong()
                    )
                )
                true
            }

            is Operation.Mutation.Upsert.UpsertOne -> {
                // TODO(): Support creating posts on the client
                throw UnsupportedOperationException()
            }
        }
    }

    private suspend fun insertOneDeleteFailed(id: Int, timestamp: Long) {
        trailsDatabase.postBookkeepingQueries.insertFailedDelete(
            PostFailedDelete(
                post_id = id.toLong(),
                timestamp = timestamp
            )
        )
    }


}