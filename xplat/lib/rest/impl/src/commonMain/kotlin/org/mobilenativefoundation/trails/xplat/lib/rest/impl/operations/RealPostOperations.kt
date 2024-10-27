package org.mobilenativefoundation.trails.xplat.lib.rest.impl.operations

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.backend.models.CompositePost
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.PostOperations
import org.mobilenativefoundation.trails.xplat.lib.rest.impl.TrailsEndpoints
import org.mobilenativefoundation.trails.xplat.lib.rest.impl.httpClient

@Inject
class RealPostOperations(
    private val httpClient: HttpClient = httpClient()
) : PostOperations {
    override suspend fun getPosts(): List<CompositePost> {
        val url = TrailsEndpoints.GET_POSTS
        val response = httpClient.get(url)
        return response.body()
    }
}