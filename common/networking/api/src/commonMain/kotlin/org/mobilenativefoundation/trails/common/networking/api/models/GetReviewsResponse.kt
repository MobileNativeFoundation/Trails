package org.mobilenativefoundation.trails.common.networking.api.models

import kotlinx.serialization.Serializable

@Serializable
data class GetReviewsResponse(
    val reviews: List<Review>,
    val pagingInfo: PagingInfo
)
