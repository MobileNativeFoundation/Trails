package org.mobilenativefoundation.trails.common.networking.api.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateHikeArgs(
    val userId: String,
    val trailId: String,
    val startPoint: String,
    val endPoint: String,
    val startTime: Long,
    val endTime: Long,
    val duration: Long,
    val distance: Float,
    val notes: String,
)