package org.mobilenativefoundation.trails.server.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class Location(
    @BsonId val id: Id<Location>,
    @BsonId val user: Id<User>,
    val oldLatitude: Float,
    val oldLongitude: Float,
    val newLatitude: Float,
    val newLongitude: Float
)