package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable
import org.mobilenativefoundation.trails.shared.paging.core.Identifiable


@Serializable
data class PostOverview(
    override val id: Int,
    val userId: Int,
    val userName: String,
    val userAvatarUrl: String,
    val hikeId: Int,
    val title: String,
    val body: String,
    val coverImageUrl: String,
    val likeIds: List<Int>,
    val commentIds: List<Int>,
) : Identifiable<Int>