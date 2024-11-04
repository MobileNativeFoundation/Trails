package org.mobilenativefoundation.trails.xplat.lib.models.post

import kotlinx.serialization.Serializable

@Serializable
sealed class PostOutput {
    data class Keys(val values: List<Post.Key>) : PostOutput()
    data class Key(val value: Post.Key) : PostOutput()

    @Serializable
    data class Single(val value: Post) : PostOutput()

    @Serializable
    data class Collection(val values: List<Post>) : PostOutput()
}