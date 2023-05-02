package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val avatarUrl: String,
    val following: List<User>,
    val followedBy: List<User>,
    val completedTrails: List<CompletedTrail>,
    val savedTrails: List<Trail>,
    val hikes: List<Hike>,
)