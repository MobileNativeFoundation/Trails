package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class CompletedTrail(
    @BsonId val id: Id<CompletedTrail>,
    @BsonId val userId: Id<User>,
    @BsonId val trailId: Id<Trail>
)