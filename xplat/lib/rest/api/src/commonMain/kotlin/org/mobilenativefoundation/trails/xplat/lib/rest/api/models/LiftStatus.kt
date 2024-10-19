package org.mobilenativefoundation.trails.xplat.lib.rest.api.models

import kotlinx.serialization.Serializable

@Serializable
data class LiftStatus(
    val id: Int? = null,
    val liftId: Int,
    val status: String,
    val statusDate: String,
    val createdAt: String? = null
)