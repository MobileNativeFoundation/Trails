package org.mobilenativefoundation.trails.xplat.lib.rest.api.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class CreateResortTagArgs(
    val userId: Int,
    val resortId: Int,
    val tagType: String,
    val createdAt: Instant
)