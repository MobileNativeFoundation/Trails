package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class LatLng(
    val latitude: Float,
    val longitude: Float
)