package org.mobilenativefoundation.trails.xplat.lib.models.post

import org.mobilenativefoundation.trails.xplat.lib.models.Model

sealed class Mention : Model<Mention.Key, Mention.Properties, Mention.Edges> {
    data class Key(val id: Int) : Model.Key
    data class Properties(
        val postId: Int,
        val mentionedUsername: String,
        val platform: Platform
    ) : Model.Properties

    data class Edges(
        val mentionedUser: Creator
    ) : Model.Edges

    data class Node(
        override val key: Key,
        override val properties: Properties
    ) : Model.Node<Key, Properties, Edges>
}