package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class PostComment(
    @BsonId val postId: Id<Post>,
    @BsonId val userId: Id<User>,
    val comment: String
)