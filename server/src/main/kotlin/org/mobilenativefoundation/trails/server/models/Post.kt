package org.mobilenativefoundation.trails.server.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class Post(
    @BsonId val id: Id<Post>,
    @BsonId val user: Id<User>,
    @BsonId val hike: Id<Hike>,
    val title: String,
    val body: String,
    @BsonId val images: List<Id<Image>>,
    @BsonId val likedBy: List<Id<User>>,
    @BsonId val comments: List<Id<PostComment>>,
)