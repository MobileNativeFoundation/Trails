package org.mobilenativefoundation.trails.shared.data.entity.flag

import kotlinx.serialization.Serializable

@Serializable
data class FeatureFlagVariation(
    val id: Int,
    val value: String
)