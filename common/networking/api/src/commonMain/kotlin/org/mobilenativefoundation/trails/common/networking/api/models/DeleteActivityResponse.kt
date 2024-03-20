package org.mobilenativefoundation.trails.common.networking.api.models

import kotlinx.serialization.Serializable

@Serializable
data class DeleteActivityResponse(
    val success: Boolean
)