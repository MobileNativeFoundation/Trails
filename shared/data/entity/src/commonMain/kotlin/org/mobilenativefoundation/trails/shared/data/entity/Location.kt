package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val id: Int,
    val user: User,
    val latitude: Float,
    val longitude: Float
)