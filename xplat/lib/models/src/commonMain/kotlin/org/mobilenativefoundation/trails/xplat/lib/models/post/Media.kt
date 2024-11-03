package org.mobilenativefoundation.trails.xplat.lib.models.post

import org.mobilenativefoundation.trails.xplat.lib.models.Model

sealed class Media : Model<Media.Key, Media.Properties, Media.Edges> {
    data class Key(val id: Int) : Model.Key

    data class Properties(
        val mediaURL: String,
        val mediaType: MediaType,
        val mediaFormat: MediaFormat?,
        val duration: Int?,
        val altText: String?,
        val height: Int?,
        val width: Int?,
    ) : Model.Properties

    data class Edges(
        val posts: List<Post.Node>
    ) : Model.Edges

    data class Node(
        override val key: Key,
        override val properties: Properties
    ) : Model.Node<Key, Properties, Creator.Edges>
}