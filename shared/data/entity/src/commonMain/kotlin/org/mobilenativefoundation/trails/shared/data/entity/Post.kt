package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,
    val user: User,
    val hike: Hike,
    val title: String,
    val body: String,
    val images: List<Image>,
    val likedBy: List<User>,
    val comments: List<PostComment>,
)