package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class Following(
    @BsonId val id: Id<Following>,
    @BsonId val followerId: Id<User>,
    @BsonId val followedId: Id<User>
)