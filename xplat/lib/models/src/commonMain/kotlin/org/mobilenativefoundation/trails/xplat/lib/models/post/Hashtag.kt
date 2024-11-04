package org.mobilenativefoundation.trails.xplat.lib.models.post

import kotlinx.serialization.Serializable
import org.mobilenativefoundation.trails.xplat.lib.models.Model

@Serializable
sealed class Hashtag : Model<Hashtag.Key, Hashtag.Properties, Hashtag.Edges> {
    @Serializable
    data class Key(val id: Int) : Model.Key
    @Serializable
    data class Properties(
        val name: String
    ) : Model.Properties
    @Serializable
    data class Node(
        override val key: Key,
        override val properties: Properties
    ) : Model.Node<Key, Properties, Edges>
    @Serializable
    data class Edges(
        val posts: List<Post.Node>
    ) : Model.Edges
}