package org.mobilenativefoundation.trails.common.networking.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Activity(
    val id: String,
    val userId: String,
    val trailId: String,
    val type: String,
    val timestamp: Long
)