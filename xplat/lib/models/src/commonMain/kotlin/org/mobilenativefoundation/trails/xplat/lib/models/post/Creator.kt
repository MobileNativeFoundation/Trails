package org.mobilenativefoundation.trails.xplat.lib.models.post

import org.mobilenativefoundation.trails.xplat.lib.models.Model

sealed interface Creator : Model<Creator.Key, Creator.Properties, Creator.Edges> {
    data class Key(val id: Int) : Model.Key

    data class Properties(
        val username: String,
        val fullName: String?,
        val profilePicURL: String?,
        val isVerified: Boolean,
        val bio: String?,
        val platform: Platform
    ) : Model.Properties

    data class Node(
        override val key: Key,
        override val properties: Properties
    ) : Model.Node<Key, Properties, Edges>, Creator

    data class Edges(
        val posts: List<Post.Node>
    ) : Model.Edges

    data class Composite(
        override val node: Node,
        override val edges: Edges
    ) : Model.Composite<Key, Properties, Edges>, Creator
}
