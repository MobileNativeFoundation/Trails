package org.mobilenativefoundation.trails.xplat.lib.rest.api.models

import kotlinx.serialization.Serializable

@Serializable
data class TrailTag(
    val id: Int? = null,
    val userId: Int,
    val trailId: Int,
    val tagType: String,
    val createdAt: String? = null
)