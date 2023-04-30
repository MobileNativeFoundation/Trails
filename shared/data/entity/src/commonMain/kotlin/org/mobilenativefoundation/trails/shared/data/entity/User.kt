package org.mobilenativefoundation.trails.shared.data.entity

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class User(
    @BsonId val id: Id<User>,
    val name: String,
    val avatarUrl: String,
)