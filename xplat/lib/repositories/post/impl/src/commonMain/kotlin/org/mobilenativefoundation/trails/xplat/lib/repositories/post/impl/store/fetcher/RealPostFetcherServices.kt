package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.fetcher

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.models.query.Order
import org.mobilenativefoundation.trails.xplat.lib.models.query.Query
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.OrderExtensions.toDomain
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostExtensions.asPostEntity
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostPredicateExtensions.toDomain
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database.PostDAO
import org.mobilenativefoundation.trails.xplat.lib.rest.api.post.PostOperations

class RealPostFetcherServices(
    private val client: PostOperations,
    private val postDAO: PostDAO,
) : PostFetcherServices {
    override suspend fun findAndEmitMany(
        operation: Operation.Query.FindMany<Post.Key>,
        emit: suspend (PostOutput) -> Unit
    ) {
        // Fetch  post from the network
        val posts = client.findMany(operation.keys)

        // Save the posts
        posts.forEach { post -> postDAO.insertOne(post.asPostEntity()) }

        val output = PostOutput.Collection(posts)
        emit(output)
    }

    override suspend fun findAndEmitOne(
        operation: Operation.Query.FindOne<Post.Key>,
        emit: suspend (PostOutput) -> Unit
    ) {
        // Fetch post from the network
        val post = client.findOne(operation.key) ?: error(404)

        // Save individual post
        postDAO.insertOne(post.asPostEntity())

        val output = PostOutput.Single(post)
        emit(output)
    }

    override suspend fun findAndEmitOneComposite(
        operation: Operation.Query.FindOneComposite<Post.Key>,
        emit: suspend (PostOutput) -> Unit
    ) {
        // Fetch composite post from the network
        val post = client.findOneComposite(operation.key) ?: error(404)

        // Save the post and associated entities
        postDAO.insertOneComposite(post)

        val output = PostOutput.Single(post)
        emit(output)
    }

    override suspend fun observeManyAndEmitUpdates(
        operation: Operation.Query.ObserveMany<Post.Key>,
        emit: suspend (PostOutput) -> Unit
    ) {
        // TODO(): Support streaming from network
        throw UnsupportedOperationException()
    }

    override suspend fun observeOneAndEmitUpdates(
        operation: Operation.Query.ObserveOne<Post.Key>,
        emit: suspend (PostOutput) -> Unit
    ) {
        // TODO(): Support streaming from network
        throw UnsupportedOperationException()
    }

    override suspend fun observeOneCompositeAndEmitUpdates(
        operation: Operation.Query.ObserveOneComposite<Post.Key>,
        emit: suspend (PostOutput) -> Unit
    ) {
        // TODO(): Support streaming from network
        throw UnsupportedOperationException()
    }

    override suspend fun queryAndEmitOne(
        operation: Operation.Query.QueryOne<Post.Key, Post.Properties, Post.Edges, Post.Node>,
        emit: suspend (PostOutput) -> Unit
    ) {
        // Fetch post from the network
        val post = client.queryOne(
            query = Query.One(
                operation.query.predicate?.toDomain(),
                operation.query.order?.toDomain()
            )
        ) ?: error(404)

        // Save the post
        postDAO.insertOne(post.asPostEntity())

        val output = PostOutput.Single(post)
        emit(output)
    }

    override suspend fun findAndEmitAll(operation: Operation.Query.FindAll, emit: suspend (PostOutput) -> Unit) {
        // Fetch  post from the network
        val posts = client.findAll()

        // Save the posts
        posts.forEach { post -> postDAO.insertOne(post.asPostEntity()) }

        val output = PostOutput.Collection(posts)
        emit(output)
    }

    override suspend fun queryAndEmitMany(
        operation: Operation.Query.QueryMany<Post.Key, Post.Properties, Post.Edges, Post.Node>,
        emit: suspend (PostOutput) -> Unit
    ) {
        // Fetch post from the network
        val posts = client.queryMany(
            query = Query.Many(
                operation.query.predicate?.toDomain(),
                operation.query.order?.toDomain(),
                operation.query.limit
            )
        )

        // Save the posts
        posts.forEach { post -> postDAO.insertOne(post.asPostEntity()) }

        val output = PostOutput.Collection(posts)
        emit(output)
    }

    override suspend fun queryAndEmitManyComposite(
        operation: Operation.Query.QueryManyComposite<Post.Key, Post.Properties, Post.Edges, Post.Node>,
        emit: suspend (PostOutput) -> Unit
    ) {
        // Fetch composite post from the network
        val order = operation.query.order?.toDomain()

        val output = client.queryManyComposite(
            Query.Many(
                predicate = operation.query.predicate?.toDomain(),
                order = order,
                limit = operation.query.limit
            )
        )

        output.values.filterIsInstance<Post.Composite>().forEach { post ->
            // Save the post and associated entities
            postDAO.insertOneComposite(post)
        }

        emit(output)
    }

}