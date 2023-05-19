package org.mobilenativefoundation.trails.server.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class PostComment(
    @BsonId val post: Id<Post>,
    @BsonId val user: Id<User>,
    val text: String
)