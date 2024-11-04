package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.fetcher

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostExtensions.asPostEntity
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostQueriesExtensions.saveComposite
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostOperation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.models.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.rest.api.post.PostOperations
import org.mobilenativefoundation.trails.xplat.lib.rest.api.query.Query.Many
import org.mobilenativefoundation.trails.xplat.lib.rest.api.query.Query.One


class PostFetcherFactory(
    private val client: PostOperations,
    private val trailsDatabase: TrailsDatabase,
) {
    fun create(): Fetcher<PostOperation, PostOutput> = Fetcher.ofFlow { operation ->
        // We never invoke fetcher after local writes
        // We do invoke updater on reads if conflicts might exist

        require(operation is Operation.Query)

        flow {
            when (operation) {
                is Operation.Query.FindMany -> findAndEmitMany(operation)
                is Operation.Query.FindOne -> findAndEmitOne(operation)
                is Operation.Query.FindOneComposite -> findAndEmitOneComposite(operation)
                is Operation.Query.ObserveMany -> observeManyAndEmitUpdates(operation)
                is Operation.Query.ObserveOne -> observeOneAndEmitUpdates(operation)
                is Operation.Query.ObserveOneComposite -> observeOneCompositeAndEmitUpdates(operation)
                is Operation.Query.QueryOne -> queryAndEmitOne(operation)
                is Operation.Query.FindAll -> findAndEmitAll()
                is Operation.Query.QueryMany -> queryAndEmitMany(operation)
                is Operation.Query.QueryManyComposite -> queryAndEmitManyComposite(operation)
            }
        }
    }

    private suspend fun FlowCollector<PostOutput>.findAndEmitMany(operation: Operation.Query.FindMany<Post.Key>) {
        // Fetch  post from the network
        val posts = client.findMany(operation.keys)

        // Save the posts
        posts.forEach { post -> trailsDatabase.postQueries.insertPost(post.asPostEntity()) }

        val output = PostOutput.Collection(posts)
        emit(output)
    }

    private suspend fun FlowCollector<PostOutput>.findAndEmitOne(operation: Operation.Query.FindOne<Post.Key>) {
        // Fetch post from the network
        val post = client.findOne(operation.key) ?: error(404)

        // Save individual post
        trailsDatabase.postQueries.insertPost(post.asPostEntity())

        val output = PostOutput.Single(post)
        emit(output)
    }

    private suspend fun FlowCollector<PostOutput>.findAndEmitOneComposite(operation: Operation.Query.FindOneComposite<Post.Key>) {
        // Fetch composite post from the network
        val post = client.findOneComposite(operation.key) ?: error(404)

        // Save the post and associated entities
        trailsDatabase.postQueries.saveComposite(post)

        val output = PostOutput.Single(post)
        emit(output)
    }

    private suspend fun FlowCollector<PostOutput>.queryAndEmitManyComposite(operation: Operation.Query.QueryManyComposite<Post.Key, Post.Properties, Post.Edges, Post.Node>) {
        // Fetch composite post from the network
        val posts = client.queryManyComposite(
            Many(
                predicate = operation.query.predicate,
                order = operation.query.order,
                limit = operation.query.limit
            )
        )

        // Save the post and associated entities
        posts.forEach { post -> trailsDatabase.postQueries.saveComposite(post) }

        val output = PostOutput.Collection(posts)
        emit(output)
    }

    private suspend fun FlowCollector<PostOutput>.queryAndEmitOne(operation: Operation.Query.QueryOne<Post.Key, Post.Properties, Post.Edges, Post.Node>) {
        // Fetch post from the network
        val post = client.queryOne(
            query = One(operation.query.predicate, operation.query.order)
        ) ?: error(404)

        // Save the post
        trailsDatabase.postQueries.insertPost(post.asPostEntity())

        val output = PostOutput.Single(post)
        emit(output)
    }

    private suspend fun FlowCollector<PostOutput>.queryAndEmitMany(operation: Operation.Query.QueryMany<Post.Key, Post.Properties, Post.Edges, Post.Node>) {
        // Fetch post from the network
        val posts = client.queryMany(
            query = Many(operation.query.predicate, operation.query.order, operation.query.limit)
        )

        // Save the posts
        posts.forEach { post -> trailsDatabase.postQueries.insertPost(post.asPostEntity()) }

        val output = PostOutput.Collection(posts)
        emit(output)
    }

    private suspend fun FlowCollector<PostOutput>.findAndEmitAll() {
        // Fetch  post from the network
        val posts = client.findAll()

        // Save the posts
        posts.forEach { post -> trailsDatabase.postQueries.insertPost(post.asPostEntity()) }

        val output = PostOutput.Collection(posts)
        emit(output)
    }

    private suspend fun FlowCollector<PostOutput>.observeManyAndEmitUpdates(operation: Operation.Query.ObserveMany<Post.Key>) {
        // TODO(): Support streaming from network
        throw UnsupportedOperationException()
    }

    private suspend fun FlowCollector<PostOutput>.observeOneAndEmitUpdates(operation: Operation.Query.ObserveOne<Post.Key>) {
        // TODO(): Support streaming from network
        throw UnsupportedOperationException()
    }

    private suspend fun FlowCollector<PostOutput>.observeOneCompositeAndEmitUpdates(operation: Operation.Query.ObserveOneComposite<Post.Key>) {
        // TODO(): Support streaming from network
        throw UnsupportedOperationException()
    }
}
