package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable


@Serializable
data class PostOverview(
    val id: String,
    val userName: String,
    val userAvatarUrl: String,
    val hikeId: String,
    val title: String,
    val body: String,
    val coverImageUrl: String,
    val likeIds: List<String>,
    val commentIds: List<String>,
)