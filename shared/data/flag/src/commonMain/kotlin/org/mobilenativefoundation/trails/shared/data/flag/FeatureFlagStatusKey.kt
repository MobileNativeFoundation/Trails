package org.mobilenativefoundation.trails.shared.data.flag

sealed class FeatureFlagStatusKey {
    data class Single(val userId: String, val key: String) : FeatureFlagStatusKey()
    data class Collection(val userId: String) : FeatureFlagStatusKey()
}