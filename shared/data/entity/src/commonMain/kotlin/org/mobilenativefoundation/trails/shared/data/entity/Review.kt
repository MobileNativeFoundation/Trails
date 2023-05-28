package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val id: Int,
    val trailId: Int,
    val userId: Int,
    val rating: Double,
    val content: String
)