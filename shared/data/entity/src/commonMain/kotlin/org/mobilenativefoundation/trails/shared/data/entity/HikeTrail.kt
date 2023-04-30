package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class HikeTrail(
    @BsonId val id: Id<HikeTrail>,
    @BsonId val hikeId: Id<Hike>,
    @BsonId val trailId: Id<Trail>
)