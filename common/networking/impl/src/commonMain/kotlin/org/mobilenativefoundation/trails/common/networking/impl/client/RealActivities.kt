package org.mobilenativefoundation.trails.common.networking.impl.client

import io.ktor.client.*
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.networking.api.client.Activities
import org.mobilenativefoundation.trails.common.networking.api.models.DeleteActivityArgs
import org.mobilenativefoundation.trails.common.networking.api.models.DeleteActivityResponse
import org.mobilenativefoundation.trails.common.networking.api.models.GetActivitiesArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetActivitiesResponse
import org.mobilenativefoundation.trails.common.networking.api.models.GetActivityArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetActivityResponse
import org.mobilenativefoundation.trails.common.networking.api.models.UpdateActivityArgs
import org.mobilenativefoundation.trails.common.networking.api.models.UpdateActivityResponse

@Inject
class RealActivities(
    private val httpClient: HttpClient,
) : Activities {

    override suspend fun getActivities(args: GetActivitiesArgs): GetActivitiesResponse {
        return httpClient.post(args, Endpoints.GET_ACTIVITIES)
    }

    override suspend fun getActivity(args: GetActivityArgs): GetActivityResponse {
        return httpClient.post(args, Endpoints.GET_ACTIVITY)
    }

    override suspend fun updateActivity(args: UpdateActivityArgs): UpdateActivityResponse {
        return httpClient.post(args, Endpoints.UPDATE_ACTIVITY)
    }

    override suspend fun deleteActivity(args: DeleteActivityArgs): DeleteActivityResponse {
        return httpClient.post(args, Endpoints.DELETE_ACTIVITY)
    }
}