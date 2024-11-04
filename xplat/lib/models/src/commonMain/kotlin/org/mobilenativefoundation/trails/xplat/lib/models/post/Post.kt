package org.mobilenativefoundation.trails.xplat.lib.models.post

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.mobilenativefoundation.trails.xplat.lib.models.Model

@Serializable
sealed interface Post : Model<Post.Key, Post.Properties, Post.Edges> {
    @Serializable
    data class Key(val id: Int) : Model.Key

    @Serializable
    data class Properties(
        val creatorId: Int,
        val caption: String?,
        val createdAt: LocalDateTime,
        val likesCount: Long,
        val commentsCount: Long,
        val sharesCount: Long,
        val viewsCount: Long,
        val isSponsored: Boolean,
        val coverURL: String,
        val platform: Platform,
        val locationName: String?
    ) : Model.Properties, Post

    @Serializable
    data class Edges(
        val creator: Creator.Node,
        val hashtags: List<Hashtag.Node>,
        val mentions: List<Mention.Node>,
        val media: List<Media.Node>
    ) : Model.Edges

    @Serializable
    data class Node(
        override val key: Key,
        override val properties: Properties
    ) : Model.Node<Key, Properties, Edges>, Post

    @Serializable
    data class Composite(
        override val node: Node,
        override val edges: Edges
    ) : Model.Composite<Key, Properties, Edges>, Post
}


val Post.Node.caption: String
    get() = this.properties.caption.orEmpty()

val Post.Node.id: Int
    get() = this.key.id

