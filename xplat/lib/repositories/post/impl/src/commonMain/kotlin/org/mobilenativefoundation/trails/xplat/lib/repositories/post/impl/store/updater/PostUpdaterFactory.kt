package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.updater

import org.mobilenativefoundation.store.store5.Updater
import org.mobilenativefoundation.store.store5.UpdaterResult
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostWriteResponse
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostOperation
import org.mobilenativefoundation.trails.xplat.lib.rest.api.post.PostOperations
import org.mobilenativefoundation.trails.xplat.lib.store.Factory

class PostUpdaterFactory(
    private val client: PostOperations,
) : Factory<Updater<PostOperation, PostOutput, PostWriteResponse>> {

    override fun create(): Updater<PostOperation, PostOutput, PostWriteResponse> = Updater.by(
        post = { operation, post ->
            handleOperation(operation, post)
        },
        onCompletion = null
    )

    private suspend fun handleOperation(operation: PostOperation, post: PostOutput): UpdaterResult {
        return when (operation) {
            is Operation.Mutation.Create.InsertOne -> {
                require(post is PostOutput.Single && post.value is Post.Properties)
                insertOne(post.value as Post.Properties)
            }

            Operation.Mutation.Delete.DeleteAll -> {
                deleteAll()
            }

            is Operation.Mutation.Delete.DeleteMany -> {
                deleteMany(operation.keys)
            }

            is Operation.Mutation.Delete.DeleteOne -> {
                deleteOne(operation.key)
            }

            is Operation.Mutation.Update.ReplaceOne -> {
                throw UnsupportedOperationException()
            }

            is Operation.Mutation.Update.UpdateOne -> {
                require(post is PostOutput.Single)
                updateOne(post)
            }

            is Operation.Mutation.Upsert.UpsertOne -> {
                require(post is PostOutput.Single && post.value is Post.Properties)
                upsertOne(post.value as Post.Properties)
            }


            // We never invoke fetcher after local writes
            // We do invoke updater on reads if conflicts might exist
            // So we need to handle query operations here too
            // If we hit any of these queries, it means we are fetching a post but bookkeeper has an unresolved sync failure for this key
            // So, before we can pull the latest value for this key from the network, we need to push our latest local value for this key to the network

            is Operation.Query -> {
                // We don't support updating many
                // So the post must be a single rather than a collection
                require(post is PostOutput.Single)
                updateOne(post, operation)
            }
        }
    }

    private suspend fun updateOne(post: PostOutput.Single): UpdaterResult {
        val count = when (val value = post.value) {
            is Post.Composite -> {
                client.updateOne(value.node)
            }

            is Post.Node -> {
                client.updateOne(value)
            }

            is Post.Properties -> {
                throw UnsupportedOperationException()
            }
        }

        return UpdaterResult.Success.Typed(PostWriteResponse.Update(count))
    }


    private suspend fun updateOne(
        post: PostOutput.Single,
        operation: Operation.Query<Post.Key, Post.Properties, Post.Edges, Post.Node>
    ): UpdaterResult {
        val count = when (val value = post.value) {
            is Post.Composite -> {
                require(operation is Operation.Query.FindOneComposite || operation is Operation.Query.ObserveOneComposite)
                client.updateOne(value.node)
            }

            is Post.Node -> {
                require(operation is Operation.Query.FindOne || operation is Operation.Query.ObserveOne || operation is Operation.Query.QueryOne)
                client.updateOne(value)
            }

            is Post.Properties -> {
                throw UnsupportedOperationException()
            }
        }

        return UpdaterResult.Success.Typed(PostWriteResponse.Update(count))
    }

    private suspend fun upsertOne(properties: Post.Properties): UpdaterResult {
        val key = client.upsertOne(properties)
        return UpdaterResult.Success.Typed(PostWriteResponse.Upsert(key))
    }

    private suspend fun insertOne(properties: Post.Properties): UpdaterResult {
        val key = client.insertOne(properties)
        return UpdaterResult.Success.Typed(PostWriteResponse.Create(key))
    }

    private suspend fun deleteOne(key: Post.Key): UpdaterResult {
        val count = client.deleteOne(key)
        return UpdaterResult.Success.Typed(PostWriteResponse.Delete(count))
    }

    private suspend fun deleteAll(): UpdaterResult {
        val count = client.deleteAll()
        return UpdaterResult.Success.Typed(PostWriteResponse.Delete(count))
    }

    private suspend fun deleteMany(keys: List<Post.Key>): UpdaterResult {
        val count = client.deleteMany(keys)
        return UpdaterResult.Success.Typed(PostWriteResponse.Delete(count))
    }


}
