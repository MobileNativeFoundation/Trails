package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
@Serializable
data class PostLike(
    @BsonId val postId: Id<Post>,
    @BsonId val userId: Id<User>
)