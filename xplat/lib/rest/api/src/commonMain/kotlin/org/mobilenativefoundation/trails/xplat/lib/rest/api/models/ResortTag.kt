package org.mobilenativefoundation.trails.xplat.lib.rest.api.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class ResortTag(
    val id: Int,
    val userId: Int,
    val resortId: Int,
    val tagType: String,
    val createdAt: Instant
)