package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable

@Serializable
sealed class Notification {

    abstract val seen: Boolean

    @Serializable
    data class Follower(
        val user: User,
        override val seen: Boolean
    ) : Notification()

    @Serializable
    data class Like(
        val user: User,
        val post: Post,
        override val seen: Boolean
    ) : Notification()

    @Serializable
    data class Comment(
        val comment: PostComment,
        override val seen: Boolean
    ) : Notification()
}