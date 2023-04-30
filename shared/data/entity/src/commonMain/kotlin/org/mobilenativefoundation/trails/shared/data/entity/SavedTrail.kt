package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class SavedTrail(
    @BsonId val id: Id<SavedTrail>,
    @BsonId val userId: Id<User>,
    @BsonId val trailId: Id<Trail>
)