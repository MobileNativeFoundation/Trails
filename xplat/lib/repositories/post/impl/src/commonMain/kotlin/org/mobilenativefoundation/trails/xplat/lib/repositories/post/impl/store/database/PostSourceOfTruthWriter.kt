package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostQueriesExtensions.saveComposite
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostOperation

class PostSourceOfTruthWriter(
    private val trailsDatabase: TrailsDatabase,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    private val coroutineScope = CoroutineScope(coroutineDispatcher)

    // We write to the SOT on mutations and queries
    // So we need to handle all cases

    suspend fun handleWrite(
        operation: PostOperation,
        value: PostOutput
    ) {

        when (operation) {
            is Operation.Mutation.Create.InsertOne -> TODO()
            Operation.Mutation.Delete.DeleteAll -> TODO()
            is Operation.Mutation.Delete.DeleteMany -> TODO()
            is Operation.Mutation.Delete.DeleteOne -> TODO()
            is Operation.Mutation.Update.ReplaceOne -> TODO()
            is Operation.Mutation.Update.UpdateOne -> TODO()
            is Operation.Mutation.Upsert.UpsertOne -> TODO()
            is Operation.Query.FindAll -> TODO()
            is Operation.Query.FindMany -> TODO()
            is Operation.Query.FindOne -> TODO()
            is Operation.Query.FindOneComposite -> TODO()
            is Operation.Query.ObserveMany -> TODO()
            is Operation.Query.ObserveOne -> TODO()
            is Operation.Query.ObserveOneComposite -> TODO()
            is Operation.Query.QueryMany -> TODO()
            is Operation.Query.QueryManyComposite -> {
                require(value is PostOutput.Collection)

                value.values.filterIsInstance<Post.Composite>().forEach { composite ->
                    trailsDatabase.postQueries.saveComposite(composite)
                }
            }

            is Operation.Query.QueryOne -> TODO()
        }

    }
}