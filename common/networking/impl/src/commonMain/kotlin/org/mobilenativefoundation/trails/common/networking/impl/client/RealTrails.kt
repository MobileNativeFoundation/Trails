package org.mobilenativefoundation.trails.common.networking.impl.client

import io.ktor.client.*
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.networking.api.client.Trails
import org.mobilenativefoundation.trails.common.networking.api.models.GetTrailArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetTrailResponse
import org.mobilenativefoundation.trails.common.networking.api.models.GetTrailsArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetTrailsResponse

@Inject
class RealTrails(
    private val httpClient: HttpClient
) : Trails {
    override suspend fun getTrails(args: GetTrailsArgs): GetTrailsResponse {
        return httpClient.post(args, Endpoints.GET_TRAILS)
    }

    override suspend fun getTrail(args: GetTrailArgs): GetTrailResponse {
        return httpClient.post(args, Endpoints.GET_TRAIL)
    }
}