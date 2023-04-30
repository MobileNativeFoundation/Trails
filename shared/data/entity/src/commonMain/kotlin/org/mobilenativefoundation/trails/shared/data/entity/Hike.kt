package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class Hike(
    @BsonId val id: Id<Hike>,
    @BsonId val userId: Id<User>,
    @BsonId val locationId: Id<Location>,
    val start: Long,
    val end: Long?,
    val eta: Long?
)