package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class CompletedTrail(
    val id: Int,
    val user: User,
    val trail: Trail,
)