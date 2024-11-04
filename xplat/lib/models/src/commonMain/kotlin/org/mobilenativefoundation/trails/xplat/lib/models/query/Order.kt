package org.mobilenativefoundation.trails.xplat.lib.models.query

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    @Serializable
    val propertyName: String,
    @Serializable
    val direction: Direction,
    @Serializable
    val propertyType: Type
)

enum class Type {STRING, INT}