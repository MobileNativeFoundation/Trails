package org.mobilenativefoundation.trails.xplat.lib.rest.api.post

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.rest.api.query.Query


interface PostOperations {
    suspend fun findOne(key: Post.Key): Post.Node?
    suspend fun findMany(keys: List<Post.Key>): List<Post.Node>
    suspend fun findAll(): List<Post.Node>

    suspend fun findOneComposite(key: Post.Key): Post.Composite?
    suspend fun findManyComposite(keys: List<Post.Key>): List<Post.Composite>

    suspend fun queryOne(query: Query.One<Post.Node>): Post.Node?
    suspend fun queryMany(query: Query.Many<Post.Node>): List<Post.Node>

    suspend fun queryManyComposite(query: Query.Many<Post.Node>): List<Post.Composite>

    suspend fun insertOne(properties: Post.Properties): Post.Key?
    suspend fun insertMany(properties: List<Post.Properties>): List<Post.Key?>

    suspend fun updateOne(node: Post.Node): Int
    suspend fun updateMany(nodes: List<Post.Node>): Int
    suspend fun upsertOne(properties: Post.Properties): Post.Key?

    suspend fun deleteOne(key: Post.Key): Int
    suspend fun deleteMany(keys: List<Post.Key>): Int
    suspend fun deleteAll(): Int

}