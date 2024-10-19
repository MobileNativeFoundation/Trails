package org.mobilenativefoundation.trails.xplat.lib.rest.api.models

import kotlinx.serialization.Serializable

@Serializable
data class ResortCondition(
    val id: Int? = null,
    val resortId: Int,
    val conditionDate: String,
    val conditions: JsonElement,
    val createdAt: String? = null
)