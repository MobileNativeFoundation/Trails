package org.mobilenativefoundation.trails.server.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class CompletedTrail(
    @BsonId val id: Id<CompletedTrail>,
    @BsonId val user: Id<User>,
    @BsonId val trail: Id<Trail>,
)