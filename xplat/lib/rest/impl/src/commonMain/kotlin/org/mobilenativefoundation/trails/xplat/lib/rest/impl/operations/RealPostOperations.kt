package org.mobilenativefoundation.trails.xplat.lib.rest.impl.operations

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.backend.models.Post
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.PostOperations
import org.mobilenativefoundation.trails.xplat.lib.rest.impl.TrailsEndpoints
import org.mobilenativefoundation.trails.xplat.lib.rest.impl.httpClient

@Inject
class RealPostOperations(
    private val httpClient: HttpClient = httpClient()
) : PostOperations {
    override suspend fun getPosts(): List<Post> {
        val url = TrailsEndpoints.GET_POSTS
        val response = httpClient.get(url)
        println(response.bodyAsText())
        return response.body()
    }
}