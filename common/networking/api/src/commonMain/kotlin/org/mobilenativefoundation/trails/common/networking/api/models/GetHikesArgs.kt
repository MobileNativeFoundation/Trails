package org.mobilenativefoundation.trails.common.networking.api.models

import kotlinx.serialization.Serializable

@Serializable
data class GetHikesArgs(
    val userId: String,
    val limit: Int,
    val cursor: String?
)
