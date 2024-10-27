package org.mobilenativefoundation.trails.xplat.lib.rest.impl.operations

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.backend.models.CompositePost
import org.mobilenativefoundation.trails.backend.models.Post
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.PostOperations
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.PostsQuery
import org.mobilenativefoundation.trails.xplat.lib.rest.impl.TrailsEndpoints
import org.mobilenativefoundation.trails.xplat.lib.rest.impl.httpClient

@Inject
class RealPostOperations(
    private val httpClient: HttpClient = httpClient()
) : PostOperations {

    override suspend fun getPost(id: Int): CompositePost? {
        val url = TrailsEndpoints.getPost(id)
        val response = httpClient.get(url)
        return response.body()
    }

    override suspend fun getPosts(query: PostsQuery): List<CompositePost> {
        val url = TrailsEndpoints.getPosts()
        val response = httpClient.get(url)
        return response.body()
    }

    override suspend fun updatePost(post: Post): Boolean {
        val url = TrailsEndpoints.updatePost(post.id)
        val response = httpClient.post(url)
        return response.body()
    }
}