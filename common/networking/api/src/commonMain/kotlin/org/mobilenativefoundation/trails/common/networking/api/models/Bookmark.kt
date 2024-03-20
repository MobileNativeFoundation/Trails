package org.mobilenativefoundation.trails.common.networking.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Bookmark(
    val id: String,
    val userId: String,
    val trailId: String,
    val createdAt: Long,
    val updatedAt: Long
)