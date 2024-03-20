package org.mobilenativefoundation.trails.common.networking.api.client

import org.mobilenativefoundation.trails.common.networking.api.models.DeleteActivityArgs
import org.mobilenativefoundation.trails.common.networking.api.models.DeleteActivityResponse
import org.mobilenativefoundation.trails.common.networking.api.models.GetActivitiesArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetActivitiesResponse
import org.mobilenativefoundation.trails.common.networking.api.models.GetActivityArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetActivityResponse
import org.mobilenativefoundation.trails.common.networking.api.models.UpdateActivityArgs
import org.mobilenativefoundation.trails.common.networking.api.models.UpdateActivityResponse

interface Activities {
    suspend fun getActivities(args: GetActivitiesArgs): GetActivitiesResponse
    suspend fun getActivity(args: GetActivityArgs): GetActivityResponse
    suspend fun updateActivity(args: UpdateActivityArgs): UpdateActivityResponse
    suspend fun deleteActivity(args: DeleteActivityArgs): DeleteActivityResponse
}