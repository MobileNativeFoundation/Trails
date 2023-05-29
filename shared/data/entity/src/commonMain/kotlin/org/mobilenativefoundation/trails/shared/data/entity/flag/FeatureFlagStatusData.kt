package org.mobilenativefoundation.trails.shared.data.entity.flag

sealed class FeatureFlagStatusData {
    data class Single(val item: FeatureFlagStatus) : FeatureFlagStatusData()
    data class Collection(val items: List<FeatureFlagStatus>) : FeatureFlagStatusData()
}
