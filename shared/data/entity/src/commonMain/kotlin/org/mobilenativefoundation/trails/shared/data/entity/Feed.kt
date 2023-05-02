package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Feed(
    val user: User,
    val posts: List<Post>
)