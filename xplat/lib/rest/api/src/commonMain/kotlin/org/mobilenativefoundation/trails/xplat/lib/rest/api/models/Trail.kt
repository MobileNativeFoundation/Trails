package org.mobilenativefoundation.trails.xplat.lib.rest.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Trail(
    val id: Int? = null,
    val resortId: Int,
    val name: String,
    val difficulty: Difficulty,
    val createdAt: String? = null,
    val updatedAt: String? = null
)