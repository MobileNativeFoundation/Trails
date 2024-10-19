package org.mobilenativefoundation.trails.xplat.lib.rest.api.models

import kotlinx.serialization.Serializable

@Serializable
data class TrailStatus(
    val id: Int? = null,
    val trailId: Int,
    val status: String,
    val statusDate: String,
    val createdAt: String? = null
)