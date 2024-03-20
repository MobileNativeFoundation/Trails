package org.mobilenativefoundation.trails.common.networking.api.client

import org.mobilenativefoundation.trails.common.networking.api.models.CreateHikeArgs
import org.mobilenativefoundation.trails.common.networking.api.models.CreateHikeResponse
import org.mobilenativefoundation.trails.common.networking.api.models.DeleteHikeArgs
import org.mobilenativefoundation.trails.common.networking.api.models.DeleteHikeResponse
import org.mobilenativefoundation.trails.common.networking.api.models.GetHikeArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetHikesArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetHikesResponse
import org.mobilenativefoundation.trails.common.networking.api.models.UpdateHikeArgs
import org.mobilenativefoundation.trails.common.networking.api.models.UpdateHikeResponse

interface Hikes {
    suspend fun createHike(args: CreateHikeArgs): CreateHikeResponse
    suspend fun getHikes(args: GetHikesArgs): GetHikesResponse
    suspend fun getHike(args: GetHikeArgs): GetHikeArgs
    suspend fun updateHike(args: UpdateHikeArgs): UpdateHikeResponse
    suspend fun deleteHike(args: DeleteHikeArgs): DeleteHikeResponse
}