package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
@Serializable
data class PostImage(
    @BsonId val postId: Id<Post>,
    @BsonId val imageId: Id<Image>
)