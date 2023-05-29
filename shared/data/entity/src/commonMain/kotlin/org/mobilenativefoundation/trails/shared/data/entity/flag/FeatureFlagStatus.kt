package org.mobilenativefoundation.trails.shared.data.entity.flag

import kotlinx.serialization.Serializable
import org.mobilenativefoundation.store.cache5.Identifiable

@Serializable
sealed class FeatureFlagStatus: Identifiable<String> {

    abstract val key: String
    abstract val lastRequested: Long
    abstract val links: Links

    @Serializable
    data class Bool(
        override val key: String,
        override val id: String = key,
        val value: Boolean,
        override val lastRequested: Long,
        override val links: Links
    ) : FeatureFlagStatus()

    @Serializable
    data class Multivariate(
        override val key: String,
        override val id: String = key,
        val value: FeatureFlagVariation,
        override val lastRequested: Long,
        override val links: Links
    ) : FeatureFlagStatus()
}