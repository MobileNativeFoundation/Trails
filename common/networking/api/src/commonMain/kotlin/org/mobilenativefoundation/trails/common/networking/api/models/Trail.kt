package org.mobilenativefoundation.trails.common.networking.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Trail(
    val id: String,
    val name: String,
    val description: String,
    val location: String,
    val length: Float,
    val difficulty: String,
    val imageUrl: String,
)