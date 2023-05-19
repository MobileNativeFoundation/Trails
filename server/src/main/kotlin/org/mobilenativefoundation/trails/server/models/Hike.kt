package org.mobilenativefoundation.trails.server.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class Hike(
    @BsonId val id: Id<Hike>,
    @BsonId val user: Id<User>,
    @BsonId val location: Id<Location>,
    val start: Long,
    val end: Long?,
    val eta: Long?,
    @BsonId val trails: List<Id<Trail>>,
)