@file:OptIn(ExperimentalStoreApi::class)

package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.store.core5.ExperimentalStoreApi
import org.mobilenativefoundation.store.store5.StoreReadRequest
import org.mobilenativefoundation.store.store5.StoreReadResponse
import org.mobilenativefoundation.store.store5.StoreWriteRequest
import org.mobilenativefoundation.store.store5.StoreWriteResponse
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.operations.query.QueryManyBuilder
import org.mobilenativefoundation.trails.xplat.lib.operations.query.QueryOneBuilder
import org.mobilenativefoundation.trails.xplat.lib.operations.query.isLocalOnly
import org.mobilenativefoundation.trails.xplat.lib.operations.query.isRemoteOnly
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.api.PostRepository
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostOperation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostStore
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.models.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.models.PostWriteResponse

@Inject
class RealPostRepository(
    private val store: PostStore
) : PostRepository {
    override suspend fun findOne(input: Operation.Query.FindOne<Post.Key>): Post.Node? {
        val output = store.firstOrNull(input) ?: return null
        require(output is PostOutput.Single && output.value is Post.Node)
        return output.value
    }

    override suspend fun findOneComposite(input: Operation.Query.FindOneComposite<Post.Key>): Post.Composite? {
        val output = store.firstOrNull(input) ?: return null
        require(output is PostOutput.Single && output.value is Post.Composite)
        return output.value
    }

    override suspend fun findMany(input: Operation.Query.FindMany<Post.Key>): List<Post.Node> {
        val output = store.firstOrNull(input) ?: return emptyList()
        require(output is PostOutput.Collection)
        val nodes = output.values.filterIsInstance<Post.Node>()
        return nodes
    }

    override suspend fun findAll(input: Operation.Query.FindAll): List<Post.Node> {
        val output = store.firstOrNull(Operation.Query.FindAll(input.dataSources)) ?: return emptyList()
        require(output is PostOutput.Collection)
        val nodes = output.values.filterIsInstance<Post.Node>()
        return nodes
    }

    override suspend fun queryOne(builder: QueryOneBuilder<Post.Node>.() -> Unit): Post.Node? {
        val query = QueryOneBuilder<Post.Node>().apply(builder).build()
        val input = Operation.Query.QueryOne(query, query.dataSources)
        val output = store.firstOrNull(input) ?: return null
        require(output is PostOutput.Single && output.value is Post.Node)
        return output.value
    }

    override suspend fun queryMany(builder: QueryManyBuilder<Post.Node>.() -> Unit): List<Post.Node> {
        val query = QueryManyBuilder<Post.Node>().apply(builder).build()
        val input = Operation.Query.QueryMany(query, query.dataSources)
        val output = store.firstOrNull(input) ?: return emptyList()
        require(output is PostOutput.Collection)
        val nodes = output.values.filterIsInstance<Post.Node>()
        return nodes
    }

    override suspend fun queryManyComposite(builder: QueryManyBuilder<Post.Node>.() -> Unit): List<Post.Composite> {
        val query = QueryManyBuilder<Post.Node>().apply(builder).build()
        val input = Operation.Query.QueryManyComposite(query, query.dataSources)

        val output = store.firstOrNull(input) ?: return emptyList()
        require(output is PostOutput.Collection)
        val nodes = output.values.filterIsInstance<Post.Composite>()
        return nodes
    }

    override suspend fun updateOne(input: Operation.Mutation.Update.UpdateOne<Post.Key, Post.Properties, Post.Edges, Post.Node>): Int {
        val request = StoreWriteRequest.of<PostOperation, PostOutput, PostWriteResponse>(
            key = input,
            value = PostOutput.Single(input.node),
        )
        return when (val response = store.write(request)) {
            is StoreWriteResponse.Error.Exception -> 0
            is StoreWriteResponse.Error.Message -> 0
            is StoreWriteResponse.Success.Typed<*> -> (response.value as? PostWriteResponse.Update)?.count ?: 0
            is StoreWriteResponse.Success.Untyped -> (response.value as? PostWriteResponse.Update)?.count ?: 0
        }
    }

    override suspend fun upsertOne(input: Operation.Mutation.Upsert.UpsertOne<Post.Properties>): Post.Key? {
        val request = StoreWriteRequest.of<PostOperation, PostOutput, PostWriteResponse>(
            key = input,
            value = PostOutput.Single(input.properties)
        )
        return when (val response = store.write(request)) {
            is StoreWriteResponse.Error -> null
            is StoreWriteResponse.Success.Typed<*> -> (response.value as? PostWriteResponse.Upsert)?.key
            is StoreWriteResponse.Success.Untyped -> (response.value as? PostWriteResponse.Upsert)?.key
        }
    }

    override suspend fun deleteOne(input: Operation.Mutation.Delete.DeleteOne<Post.Key>): Int {
        val request = StoreWriteRequest.of<PostOperation, PostOutput, PostWriteResponse>(
            key = input,
            value = PostOutput.Key(input.key)
        )
        return when (val response = store.write(request)) {
            is StoreWriteResponse.Error -> 0
            is StoreWriteResponse.Success.Typed<*> -> (response.value as? PostWriteResponse.Delete)?.count ?: 0
            is StoreWriteResponse.Success.Untyped -> (response.value as? PostWriteResponse.Delete)?.count ?: 0
        }
    }

    override suspend fun deleteMany(input: Operation.Mutation.Delete.DeleteMany<Post.Key>): Int {
        val request = StoreWriteRequest.of<PostOperation, PostOutput, PostWriteResponse>(
            key = input,
            value = PostOutput.Keys(input.keys)
        )
        return when (val response = store.write(request)) {
            is StoreWriteResponse.Error -> 0
            is StoreWriteResponse.Success.Typed<*> -> (response.value as? PostWriteResponse.Delete)?.count ?: 0
            is StoreWriteResponse.Success.Untyped -> (response.value as? PostWriteResponse.Delete)?.count ?: 0
        }
    }

    override suspend fun deleteAll(): Int {
        val request = StoreWriteRequest.of<PostOperation, PostOutput, PostWriteResponse>(
            key = Operation.Mutation.Delete.DeleteAll,
            value = PostOutput.Keys(emptyList())
        )
        return when (val response = store.write(request)) {
            is StoreWriteResponse.Error -> 0
            is StoreWriteResponse.Success.Typed<*> -> (response.value as? PostWriteResponse.Delete)?.count ?: 0
            is StoreWriteResponse.Success.Untyped -> (response.value as? PostWriteResponse.Delete)?.count ?: 0
        }
    }

    override fun observeOne(input: Operation.Query.ObserveOne<Post.Key>): Flow<Post.Node?> {
        throw UnsupportedOperationException()
    }

    private suspend fun PostStore.firstOrNull(input: Operation.Query<Post.Key, Post.Properties, Post.Edges, Post.Node>): PostOutput? {

        val request = when {
            input.dataSources.isRemoteOnly() -> StoreReadRequest.fresh(input)
            input.dataSources.isLocalOnly() -> StoreReadRequest.localOnly(input)
            else -> StoreReadRequest.cached(input, refresh = true)
        }

        return stream<Boolean>(request).firstOrNull {
            it is StoreReadResponse.Data
        }?.dataOrNull()
    }
}


