package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Trail(
    val id: String,
    val name: String,
    val route: List<LatLng>,
    val bounds: LatLngBounds,
    val difficulty: String,
    val rating: Double,
    val reviews: List<Review>,
    val location: String,
    val lengthInFeet: Int,
    val estimatedMinToFinish: Int
)