package org.mobilenativefoundation.trails.xplat.lib.models.post

import org.mobilenativefoundation.trails.xplat.lib.models.Model


sealed class Hashtag : Model<Hashtag.Key, Hashtag.Properties, Hashtag.Edges> {
    data class Key(val id: Int) : Model.Key
    data class Properties(
        val name: String
    ) : Model.Properties

    data class Node(
        override val key: Key,
        override val properties: Properties
    ) : Model.Node<Key, Properties, Edges>

    data class Edges(
        val posts: List<Post.Node>
    ) : Model.Edges
}