package org.mobilenativefoundation.trails.common.networking.impl.client

import io.ktor.client.*
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.networking.api.client.Hikes
import org.mobilenativefoundation.trails.common.networking.api.models.CreateHikeArgs
import org.mobilenativefoundation.trails.common.networking.api.models.CreateHikeResponse
import org.mobilenativefoundation.trails.common.networking.api.models.DeleteHikeArgs
import org.mobilenativefoundation.trails.common.networking.api.models.DeleteHikeResponse
import org.mobilenativefoundation.trails.common.networking.api.models.GetHikeArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetHikesArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetHikesResponse
import org.mobilenativefoundation.trails.common.networking.api.models.UpdateHikeArgs
import org.mobilenativefoundation.trails.common.networking.api.models.UpdateHikeResponse

@Inject
class RealHikes(
    private val httpClient: HttpClient
) : Hikes {
    override suspend fun createHike(args: CreateHikeArgs): CreateHikeResponse {
        return httpClient.post(args, Endpoints.CREATE_HIKE)
    }

    override suspend fun getHikes(args: GetHikesArgs): GetHikesResponse {
        return httpClient.post(args, Endpoints.GET_HIKES)
    }

    override suspend fun getHike(args: GetHikeArgs): GetHikeArgs {
        return httpClient.post(args, Endpoints.GET_HIKE)
    }

    override suspend fun updateHike(args: UpdateHikeArgs): UpdateHikeResponse {
        return httpClient.post(args, Endpoints.UPDATE_HIKE)
    }

    override suspend fun deleteHike(args: DeleteHikeArgs): DeleteHikeResponse {
        return httpClient.post(args, Endpoints.DELETE_HIKE)
    }
}