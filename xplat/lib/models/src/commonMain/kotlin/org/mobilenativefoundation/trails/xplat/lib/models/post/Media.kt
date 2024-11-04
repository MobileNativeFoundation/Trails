package org.mobilenativefoundation.trails.xplat.lib.models.post

import kotlinx.serialization.Serializable
import org.mobilenativefoundation.trails.xplat.lib.models.Model
@Serializable
sealed class Media : Model<Media.Key, Media.Properties, Media.Edges> {
    @Serializable
    data class Key(val id: Int) : Model.Key
    @Serializable
    data class Properties(
        val mediaURL: String,
        val mediaType: MediaType,
        val mediaFormat: MediaFormat?,
        val duration: Int?,
        val altText: String?,
        val height: Int?,
        val width: Int?,
    ) : Model.Properties
    @Serializable
    data class Edges(
        val posts: List<Post.Node>
    ) : Model.Edges
    @Serializable
    data class Node(
        override val key: Key,
        override val properties: Properties
    ) : Model.Node<Key, Properties, Creator.Edges>
}