package org.mobilenativefoundation.trails.xplat.lib.rest.api.models

import kotlinx.serialization.Serializable

@Serializable
data class UserFollow(
    val id: Int? = null,
    val followerId: Int,
    val followeeId: Int,
    val createdAt: String? = null
)