package org.mobilenativefoundation.trails.server.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class User(
    @BsonId val id: Id<User>,
    val name: String,
    val avatarUrl: String,
    @BsonId val following: List<Id<User>>,
    @BsonId val followedBy: List<Id<User>>,
    @BsonId val completedTrails: List<Id<CompletedTrail>>,
    @BsonId val savedTrails: List<Id<Trail>>,
    @BsonId val hikes: List<Id<Hike>>,
    @BsonId val feed: List<Id<Post>>
)