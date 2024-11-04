package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.fetcher

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.query.Order
import org.mobilenativefoundation.trails.xplat.lib.models.query.Query.Many
import org.mobilenativefoundation.trails.xplat.lib.models.query.Query.One
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Predicate
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostExtensions.asPostEntity
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostQueriesExtensions.saveComposite
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostOperation
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.rest.api.post.PostOperations


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

    private fun convertPredicate(predicate: Predicate<Post.Node, *>): org.mobilenativefoundation.trails.xplat.lib.models.query.Predicate {
        return when (predicate) {
            is Predicate.Comparison -> {

                println("PREDICATE = $predicate")
                println("PREDICATE PROPERTY = ${predicate.property.name.removePrefix("property ")}")
                println("PREDICATE PROPERTY NAME = ${predicate.property::name}")
                when (predicate.value) {
                    is String -> org.mobilenativefoundation.trails.xplat.lib.models.query.Predicate.Comparison.StringComparison(
                        propertyName = predicate.property.name.removePrefix("property "),
                        operator = predicate.operator,
                        value = predicate.value as String,
                    )

                    is Boolean -> org.mobilenativefoundation.trails.xplat.lib.models.query.Predicate.Comparison.BooleanComparison(
                        propertyName = predicate.property.name,
                        operator = predicate.operator,
                        value = predicate.value as Boolean,
                    )

                    is Int -> org.mobilenativefoundation.trails.xplat.lib.models.query.Predicate.Comparison.IntComparison(
                        propertyName = predicate.property.name.removePrefix("property "),
                        operator = predicate.operator,
                        value = predicate.value as Int,
                    )

                    else -> error("Unsupported type.")
                }


            }

            is Predicate.Logical -> {
                org.mobilenativefoundation.trails.xplat.lib.models.query.Predicate.Logical(
                    operator = predicate.operator,
                    predicates = predicate.predicates.map { convertPredicate(it) }
                )

            }
        }

    }


    private fun convertOrder(order: org.mobilenativefoundation.trails.xplat.lib.operations.query.Order<*>?): Order? {
        return order?.let {
            Order(
                propertyName = it.property.name,
                direction = it.direction,
                propertyType = it.propertyType
            )
        }
    }


    private suspend fun FlowCollector<PostOutput>.queryAndEmitManyComposite(operation: Operation.Query.QueryManyComposite<Post.Key, Post.Properties, Post.Edges, Post.Node>) {
        // Fetch composite post from the network
        val order = operation.query.order?.let {
            convertOrder(it)
        }

        val posts = client.queryManyComposite(
            Many(
                predicate = operation.query.predicate?.let { convertPredicate(it) },
                order = order,
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
            query = One(
                operation.query.predicate?.let { convertPredicate(it) },
                operation.query.order?.let { convertOrder(it) })
        ) ?: error(404)

        // Save the post
        trailsDatabase.postQueries.insertPost(post.asPostEntity())

        val output = PostOutput.Single(post)
        emit(output)
    }

    private suspend fun FlowCollector<PostOutput>.queryAndEmitMany(operation: Operation.Query.QueryMany<Post.Key, Post.Properties, Post.Edges, Post.Node>) {
        // Fetch post from the network
        val posts = client.queryMany(
            query = Many(
                operation.query.predicate?.let { convertPredicate(it) },
                operation.query.order?.let { convertOrder(it) },
                operation.query.limit
            )
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
