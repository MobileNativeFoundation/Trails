package org.mobilenativefoundation.trails.xplat.lib.rest.api.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int? = null,
    val username: String,
    val email: String,
    val passwordHash: String,
    val createdAt: String? = null,
    val updatedAt: String? = null
)

