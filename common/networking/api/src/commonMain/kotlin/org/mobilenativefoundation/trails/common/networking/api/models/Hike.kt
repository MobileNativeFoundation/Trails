package org.mobilenativefoundation.trails.common.networking.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Hike(
    val id: String,
    val userId: String,
    val trailId: String,
    val startPoint: String,
    val endPoint: String,
    val startTime: Long,
    val endTime: Long,
    val duration: Long,
    val distance: Long,
    val notes: String,
    val createdAt: Long,
    val updatedAt: Long
)