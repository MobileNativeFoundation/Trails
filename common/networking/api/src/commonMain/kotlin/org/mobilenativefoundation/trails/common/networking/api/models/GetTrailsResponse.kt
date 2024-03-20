package org.mobilenativefoundation.trails.common.networking.api.models

import kotlinx.serialization.Serializable

@Serializable
data class GetTrailsResponse(
    val trails: List<Trail>,
    val pagingInfo: PagingInfo,
)