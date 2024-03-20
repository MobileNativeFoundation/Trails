package org.mobilenativefoundation.trails.common.networking.api.models

import kotlinx.serialization.Serializable

@Serializable
data class GetHikesResponse(
    val hikes: List<Hike>,
    val pagingInfo: PagingInfo
)
