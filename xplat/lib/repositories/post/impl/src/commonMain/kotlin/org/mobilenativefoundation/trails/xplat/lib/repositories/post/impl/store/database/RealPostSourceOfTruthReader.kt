package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Query
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostExtensions.asNode


class RealPostSourceOfTruthReader(
    private val postDAO: PostDAO,
    private val predicateEvaluator: PostPredicateEvaluator,
    private val comparer: PostComparer,
    coroutineDispatcher: CoroutineDispatcher
): PostSourceOfTruthReader {
    private val coroutineScope = CoroutineScope(coroutineDispatcher)



    override fun findOne(operation: Operation.Query.FindOne<Post.Key>, emit: suspend (PostOutput.Single?) -> Unit) {
        coroutineScope.launch {
            val id = operation.key.id
            val postEntity = postDAO.findOne(id)
            val postOutput = postEntity?.asNode()?.let { PostOutput.Single(it) }
            emit(postOutput)
        }
    }

    override fun queryManyComposite(
        query: Query.Many<Post.Node>,
        emit: suspend (PostOutput.Collection?) -> Unit
    ) {
        coroutineScope.launch {

            val entities = postDAO.findAll()

            val composites = entities.map { it.asNode() }.asSequence()
                .filter { item -> query.predicate?.let { predicateEvaluator.evaluate(it, item) } ?: true }
                .sortedWith { a, b -> comparer.compare(a, b, query.order) }
                .let { sequence ->
                    query.limit?.let { sequence.take(it) } ?: sequence
                }.mapNotNull {
                    postDAO.findOneAndAssembleComposite(it.id)
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
}