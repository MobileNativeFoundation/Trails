package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class Post(
    @BsonId val id: Id<Post>,
    @BsonId val userId: Id<User>,
    @BsonId val hikeId: Id<Hike>,
    val title: String,
    val body: String,
)