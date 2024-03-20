package org.mobilenativefoundation.trails.common.networking.api.models

import kotlinx.serialization.Serializable

@Serializable
data class GetBookmarksResponse(
    val bookmarks: List<Bookmark>,
    val pagingInfo: PagingInfo
)
