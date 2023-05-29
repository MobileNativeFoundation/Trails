package org.mobilenativefoundation.trails.shared.data.entity.flag

import kotlinx.serialization.Serializable

@Serializable
data class FeatureFlagStatuses(
    val items: List<FeatureFlagStatus>
)