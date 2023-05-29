package org.mobilenativefoundation.trails.shared.data.flag

sealed class FeatureFlagStatusKey {
    data class Single(val userId: Int, val key: String) : FeatureFlagStatusKey()
    data class Collection(val userId: Int) : FeatureFlagStatusKey()
}

internal fun FeatureFlagStatusKey.local() = when (this) {
    is FeatureFlagStatusKey.Collection -> "$userId"
    is FeatureFlagStatusKey.Single -> "${userId}_$key"
}