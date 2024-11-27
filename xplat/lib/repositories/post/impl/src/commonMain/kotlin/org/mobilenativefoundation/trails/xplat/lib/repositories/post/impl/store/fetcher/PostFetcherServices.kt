package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.fetcher

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation

interface PostFetcherServices {
    suspend fun findAndEmitMany(operation: Operation.Query.FindMany<Post.Key>, emit: suspend (PostOutput) -> Unit)
    suspend fun findAndEmitOne(operation: Operation.Query.FindOne<Post.Key>, emit: suspend (PostOutput) -> Unit)
    suspend fun findAndEmitOneComposite(
        operation: Operation.Query.FindOneComposite<Post.Key>,
        emit: suspend (PostOutput) -> Unit
    )

    suspend fun observeManyAndEmitUpdates(
        operation: Operation.Query.ObserveMany<Post.Key>,
        emit: suspend (PostOutput) -> Unit
    )

    suspend fun observeOneAndEmitUpdates(
        operation: Operation.Query.ObserveOne<Post.Key>,
        emit: suspend (PostOutput) -> Unit
    )

    suspend fun observeOneCompositeAndEmitUpdates(
        operation: Operation.Query.ObserveOneComposite<Post.Key>,
        emit: suspend (PostOutput) -> Unit
    )

    suspend fun queryAndEmitOne(
        operation: Operation.Query.QueryOne<Post.Key, Post.Properties, Post.Edges, Post.Node>,
        emit: suspend (PostOutput) -> Unit
    )

    suspend fun findAndEmitAll(operation: Operation.Query.FindAll, emit: suspend (PostOutput) -> Unit)
    suspend fun queryAndEmitMany(
        operation: Operation.Query.QueryMany<Post.Key, Post.Properties, Post.Edges, Post.Node>,
        emit: suspend (PostOutput) -> Unit
    )

    suspend fun queryAndEmitManyComposite(
        operation: Operation.Query.QueryManyComposite<Post.Key, Post.Properties, Post.Edges, Post.Node>,
        emit: suspend (PostOutput) -> Unit
    )
}