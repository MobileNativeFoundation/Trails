package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Hike(
    val id: String,
    val user: User,
    val location: Location,
    val start: Long,
    val end: Long?,
    val eta: Long?,
    val trails: List<Trail>,
)