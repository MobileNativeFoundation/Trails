package org.mobilenativefoundation.trails.common.networking.api.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateReviewArgs(
    val userId: String,
    val trailId: String,
    val rating: Int,
    val comment: String,
)
