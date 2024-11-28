package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation

class RealPostSourceOfTruthWriter(
    private val postDAO: PostDAO
) : PostSourceOfTruthWriter {

    override suspend fun queryManyComposite(
        operation: Operation.Query.QueryManyComposite<Post.Key, Post.Properties, Post.Edges, Post.Node>,
        value: PostOutput
    ) {
        require(value is PostOutput.Collection)

        value.values.filterIsInstance<Post.Composite>().forEach { composite ->
            postDAO.insertOneComposite(composite)
        }
    }
}