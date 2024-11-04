package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database


import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.id
import org.mobilenativefoundation.trails.xplat.lib.models.query.ComparisonOperator
import org.mobilenativefoundation.trails.xplat.lib.models.query.Direction
import org.mobilenativefoundation.trails.xplat.lib.models.query.LogicalOperator
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Order
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Predicate
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Query
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostExtensions.asNode
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostQueriesExtensions.assembleCompositePost
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.models.PostOutput

class PostSourceOfTruthReader(
    private val trailsDatabase: TrailsDatabase,
    coroutineDispatcher: CoroutineDispatcher
) {
    private val coroutineScope = CoroutineScope(coroutineDispatcher)

    fun handleRead(
        operation: Operation.Query<Post.Key, Post.Properties, Post.Edges, Post.Node>,
        emit: suspend (PostOutput?) -> Unit
    ) {

        when (operation) {
            is Operation.Query.FindAll -> TODO()
            is Operation.Query.FindMany -> TODO()
            is Operation.Query.FindOne -> findOne(operation, emit)
            is Operation.Query.FindOneComposite -> TODO()
            is Operation.Query.ObserveMany -> TODO()
            is Operation.Query.ObserveOne -> TODO()
            is Operation.Query.ObserveOneComposite -> TODO()
            is Operation.Query.QueryMany -> TODO()
            is Operation.Query.QueryOne -> TODO()
            is Operation.Query.QueryManyComposite -> queryManyComposite(operation.query, emit)
        }
    }

    private fun findOne(operation: Operation.Query.FindOne<Post.Key>, emit: suspend (PostOutput.Single?) -> Unit) {
        val id = operation.key.id
        coroutineScope.launch {
            val postEntity = trailsDatabase.postQueries.selectPostById(id.toLong()).executeAsOneOrNull()
            val postOutput = postEntity?.asNode()?.let { PostOutput.Single(it) }
            emit(postOutput)
        }
    }

    private fun queryManyComposite(
        query: Query.Many<Post.Node>,
        emit: suspend (PostOutput.Collection?) -> Unit
    ) {
        coroutineScope.launch {

            val entities = trailsDatabase.postQueries.findAllPosts().executeAsList()


            val composites = entities.map { it.asNode() }.asSequence()
                .filter { item -> query.predicate?.let { evaluatePredicate(it, item) } ?: true }
                .sortedWith { a, b -> compareItems(a, b, query.order) }
                .let { sequence ->
                    query.limit?.let { sequence.take(it) } ?: sequence
                }.mapNotNull {
                    trailsDatabase.postQueries.assembleCompositePost(it.id.toLong())
                }.toList()

            emit(
                if (composites.isEmpty()) {
                    null
                } else {
                    PostOutput.Collection(composites)
                }
            )
        }
    }


    private fun evaluatePredicate(predicate: Predicate<Post.Node, *>, item: Post.Node): Boolean {
        return when (predicate) {
            is Predicate.Comparison<Post.Node, *> -> {

                when (predicate.property.get(item)) {
                    is String -> {
                        val propertyValue = predicate.property.get(item) as String
                        val comparisonValue = predicate.value as String

                        when (predicate.operator) {
                            ComparisonOperator.EQUALS -> propertyValue == comparisonValue
                            ComparisonOperator.NOT_EQUALS -> propertyValue != comparisonValue
                            ComparisonOperator.GREATER_THAN -> propertyValue > comparisonValue
                            ComparisonOperator.LESS_THAN -> propertyValue < comparisonValue
                            ComparisonOperator.GREATER_THAN_OR_EQUALS -> propertyValue >= comparisonValue
                            ComparisonOperator.LESS_THAN_OR_EQUALS -> propertyValue <= comparisonValue
                            ComparisonOperator.CONTAINS -> propertyValue.contains(comparisonValue)
                        }
                    }

                    is Boolean -> {
                        val propertyValue = predicate.property.get(item) as Boolean
                        val comparisonValue = predicate.value as Boolean

                        when (predicate.operator) {
                            ComparisonOperator.EQUALS -> propertyValue == comparisonValue
                            ComparisonOperator.NOT_EQUALS -> propertyValue != comparisonValue
                            else -> throw UnsupportedOperationException("Boolean comparison is limited to equality.")
                        }
                    }

                    is Int -> {
                        val propertyValue = predicate.property.get(item) as Int
                        val comparisonValue = predicate.value as Int

                        when (predicate.operator) {
                            ComparisonOperator.EQUALS -> propertyValue == comparisonValue
                            ComparisonOperator.NOT_EQUALS -> propertyValue != comparisonValue
                            ComparisonOperator.GREATER_THAN -> propertyValue > comparisonValue
                            ComparisonOperator.LESS_THAN -> propertyValue < comparisonValue
                            ComparisonOperator.GREATER_THAN_OR_EQUALS -> propertyValue >= comparisonValue
                            ComparisonOperator.LESS_THAN_OR_EQUALS -> propertyValue <= comparisonValue
                            ComparisonOperator.CONTAINS -> throw UnsupportedOperationException("One integer cannot contain another integer.")
                        }
                    }

                    is Long -> {
                        val propertyValue = predicate.property.get(item) as Long
                        val comparisonValue = predicate.value as Long

                        when (predicate.operator) {
                            ComparisonOperator.EQUALS -> propertyValue == comparisonValue
                            ComparisonOperator.NOT_EQUALS -> propertyValue != comparisonValue
                            ComparisonOperator.GREATER_THAN -> propertyValue > comparisonValue
                            ComparisonOperator.LESS_THAN -> propertyValue < comparisonValue
                            ComparisonOperator.GREATER_THAN_OR_EQUALS -> propertyValue >= comparisonValue
                            ComparisonOperator.LESS_THAN_OR_EQUALS -> propertyValue <= comparisonValue
                            ComparisonOperator.CONTAINS -> throw UnsupportedOperationException("One long cannot contain another long.")
                        }
                    }

                    else -> {
                        throw UnsupportedOperationException("Querying support is limited to comparisons between primitives of the same type.")
                    }
                }
            }

            is Predicate.Logical -> {
                val evaluations = predicate.predicates.map { evaluatePredicate(it, item) }
                when (predicate.operator) {
                    LogicalOperator.AND -> evaluations.all { it }
                    LogicalOperator.OR -> evaluations.any { it }
                }
            }
        }
    }

    private fun compareItems(a: Post.Node, b: Post.Node, order: Order<Post.Node>?): Int {
        return if (order != null) {

            when (val type = order.property.get(a)) {
                is String -> {
                    val valueA = order.property.get(a) as String
                    val valueB = order.property.get(b) as String
                    val comparison = valueA.compareTo(valueB)
                    if (order.direction == Direction.ASC) comparison else -comparison
                }

                is Int -> {
                    val valueA = order.property.get(a) as Int
                    val valueB = order.property.get(b) as Int
                    val comparison = valueA.compareTo(valueB)
                    if (order.direction == Direction.ASC) comparison else -comparison
                }

                is Long -> {
                    val valueA = order.property.get(a) as Long
                    val valueB = order.property.get(b) as Long
                    val comparison = valueA.compareTo(valueB)
                    if (order.direction == Direction.ASC) comparison else -comparison
                }

                else -> {
                    throw UnsupportedOperationException("Received ${type?.let { it::class.simpleName }}")
                }
            }
        } else {
            0
        }
    }
}