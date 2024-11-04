package org.mobilenativefoundation.trails.xplat.lib.models.post

import kotlinx.serialization.Serializable
import org.mobilenativefoundation.trails.xplat.lib.models.Model

@Serializable
sealed interface Creator : Model<Creator.Key, Creator.Properties, Creator.Edges> {
    @Serializable
    data class Key(val id: Int) : Model.Key
    @Serializable
    data class Properties(
        val username: String,
        val fullName: String?,
        val profilePicURL: String?,
        val isVerified: Boolean,
        val bio: String?,
        val platform: Platform
    ) : Model.Properties
    @Serializable
    data class Node(
        override val key: Key,
        override val properties: Properties
    ) : Model.Node<Key, Properties, Edges>, Creator
    @Serializable
    data class Edges(
        val posts: List<Post.Node>
    ) : Model.Edges
    @Serializable
    data class Composite(
        override val node: Node,
        override val edges: Edges
    ) : Model.Composite<Key, Properties, Edges>, Creator
}
