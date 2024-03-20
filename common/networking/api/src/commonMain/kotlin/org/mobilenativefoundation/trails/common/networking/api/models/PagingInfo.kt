package org.mobilenativefoundation.trails.common.networking.api.models

import kotlinx.serialization.Serializable

@Serializable
data class PagingInfo(
    val totalCount: Int,
    val hasMore: Boolean,
    val nextCursor: String?,
    val prevCursor: String?,
    val currentPage: Int,
    val totalPages: Int,
)