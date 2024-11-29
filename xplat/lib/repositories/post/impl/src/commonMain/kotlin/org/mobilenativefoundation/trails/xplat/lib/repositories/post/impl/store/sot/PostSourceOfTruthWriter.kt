package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.sot

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation

interface PostSourceOfTruthWriter {
    suspend fun queryManyComposite(
        operation: Operation.Query.QueryManyComposite<Post.Key, Post.Properties, Post.Edges, Post.Node>,
        value: PostOutput
    )
}


