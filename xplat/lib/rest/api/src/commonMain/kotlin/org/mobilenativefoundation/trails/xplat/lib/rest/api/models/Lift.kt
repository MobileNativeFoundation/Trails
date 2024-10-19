package org.mobilenativefoundation.trails.xplat.lib.rest.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Lift(
    val id: Int? = null,
    val resortId: Int,
    val name: String,
    val createdAt: String? = null,
    val updatedAt: String? = null
)