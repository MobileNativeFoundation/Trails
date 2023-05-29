package org.mobilenativefoundation.trails.shared.data.entity.flag

import kotlinx.serialization.Serializable

@Serializable
sealed class FeatureFlagStatus {

    abstract val key: String
    abstract val lastRequested: Long
    abstract val links: Links

    @Serializable
    data class Bool(
        override val key: String,
        val value: Boolean,
        override val lastRequested: Long,
        override val links: Links
    ) : FeatureFlagStatus()

    @Serializable
    data class Multivariate(
        override val key: String,
        val value: FeatureFlagVariation,
        override val lastRequested: Long,
        override val links: Links
    ) : FeatureFlagStatus()
}