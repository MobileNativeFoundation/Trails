package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class LatLngBounds(
    val southwest: LatLng,
    val northeast: LatLng
)