package org.mobilenativefoundation.trails.common.networking.api.client

import org.mobilenativefoundation.trails.common.networking.api.models.GetTrailArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetTrailResponse
import org.mobilenativefoundation.trails.common.networking.api.models.GetTrailsArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetTrailsResponse

interface Trails {
    suspend fun getTrails(args: GetTrailsArgs): GetTrailsResponse
    suspend fun getTrail(args: GetTrailArgs): GetTrailResponse
}