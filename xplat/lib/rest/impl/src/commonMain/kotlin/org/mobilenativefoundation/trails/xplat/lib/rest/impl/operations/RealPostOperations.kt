package org.mobilenativefoundation.trails.xplat.lib.rest.impl.operations

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.query.Query
import org.mobilenativefoundation.trails.xplat.lib.rest.api.post.PostOperations
import org.mobilenativefoundation.trails.xplat.lib.rest.impl.TrailsEndpoints
import org.mobilenativefoundation.trails.xplat.lib.rest.impl.httpClient

@Inject
class RealPostOperations(
    private val httpClient: HttpClient = httpClient()
) : PostOperations {
    override suspend fun findOne(key: Post.Key): Post.Node? {
        val url = TrailsEndpoints.getPost(key.id)
        val response = httpClient.get(url)
        return response.body()
    }

    override suspend fun findMany(keys: List<Post.Key>): List<Post.Node> {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<Post.Node> {
        val url = TrailsEndpoints.getPosts()
        val response = httpClient.get(url)
        return response.body()
    }

    override suspend fun findOneComposite(key: Post.Key): Post.Composite? {
        TODO("Not yet implemented")
    }

    override suspend fun findManyComposite(keys: List<Post.Key>): List<Post.Composite> {
        TODO("Not yet implemented")
    }

    override suspend fun queryOne(query: Query.One): Post.Node? {
        TODO("Not yet implemented")
    }

    override suspend fun queryMany(query: Query.Many): List<Post.Node> {
        TODO("Not yet implemented")
    }

    override suspend fun queryManyComposite(query: Query.Many): List<Post.Composite> {
        val url = TrailsEndpoints.queryPostsComposite()
        val response = httpClient.post(url) {
            setBody(query)
            contentType(ContentType.Application.Json)
        }
        return response.body()
    }

    override suspend fun insertOne(properties: Post.Properties): Post.Key? {
        TODO("Not yet implemented")
    }

    override suspend fun insertMany(properties: List<Post.Properties>): List<Post.Key?> {
        TODO("Not yet implemented")
    }

    override suspend fun updateOne(node: Post.Node): Int {
        val url = TrailsEndpoints.updatePost(node.key.id)
        val response = httpClient.post(url)
        return response.body()
    }

    override suspend fun updateMany(nodes: List<Post.Node>): Int {
        TODO("Not yet implemented")
    }

    override suspend fun upsertOne(properties: Post.Properties): Post.Key? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOne(key: Post.Key): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMany(keys: List<Post.Key>): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll(): Int {
        TODO("Not yet implemented")
    }


}