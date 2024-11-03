package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostExtensions.asNode
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
            is Operation.Query.QueryManyComposite -> TODO()
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
}