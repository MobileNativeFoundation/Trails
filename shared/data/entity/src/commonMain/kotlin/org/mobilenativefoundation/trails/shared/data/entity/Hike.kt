package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Hike(
    val id: Int,
    val trail: Trail,
    val user: User,
    val path: List<LatLng>,
    val start: Long,
    val end: Long?,
)