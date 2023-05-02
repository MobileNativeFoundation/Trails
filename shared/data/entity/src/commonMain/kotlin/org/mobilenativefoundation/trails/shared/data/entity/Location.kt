package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val id: String,
    val user: User,
    val oldLatitude: Float,
    val oldLongitude: Float,
    val newLatitude: Float,
    val newLongitude: Float
)