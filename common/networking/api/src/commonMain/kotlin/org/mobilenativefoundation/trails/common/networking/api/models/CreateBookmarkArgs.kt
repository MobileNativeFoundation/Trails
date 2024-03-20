package org.mobilenativefoundation.trails.common.networking.api.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateBookmarkArgs(
    val trailId: String,
    val userId: String
)