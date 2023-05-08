package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val id: String,
    val trailId: String,
    val userId: String,
    val rating: Double,
    val content: String
)