package org.mobilenativefoundation.trails.xplat.lib.models.post

import kotlinx.serialization.Serializable
import org.mobilenativefoundation.trails.xplat.lib.models.Model
@Serializable
sealed class Mention : Model<Mention.Key, Mention.Properties, Mention.Edges> {
    @Serializable
    data class Key(val id: Int) : Model.Key
    @Serializable
    data class Properties(
        val postId: Int,
        val mentionedUsername: String,
        val platform: Platform
    ) : Model.Properties
    @Serializable
    data class Edges(
        val mentionedUser: Creator
    ) : Model.Edges
    @Serializable
    data class Node(
        override val key: Key,
        override val properties: Properties
    ) : Model.Node<Key, Properties, Edges>
}