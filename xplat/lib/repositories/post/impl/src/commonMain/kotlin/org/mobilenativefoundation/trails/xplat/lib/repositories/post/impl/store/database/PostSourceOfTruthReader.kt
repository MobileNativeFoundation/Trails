package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database


import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Query


interface PostSourceOfTruthReader {
    fun findOne(operation: Operation.Query.FindOne<Post.Key>, emit: suspend (PostOutput.Single?) -> Unit)
    fun queryManyComposite(
        query: Query.Many<Post.Node>,
        emit: suspend (PostOutput.Collection?) -> Unit
    )
}


