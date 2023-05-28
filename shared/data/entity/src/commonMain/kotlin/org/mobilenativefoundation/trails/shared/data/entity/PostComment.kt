package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class PostComment(
    val id: Int,
    val post: Post,
    val user: User,
    val text: String
)