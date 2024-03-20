package org.mobilenativefoundation.trails.common.networking.api.models

import kotlinx.serialization.Serializable

@Serializable
data class DeleteBookmarkArgs(
    val bookmarkId: String
)