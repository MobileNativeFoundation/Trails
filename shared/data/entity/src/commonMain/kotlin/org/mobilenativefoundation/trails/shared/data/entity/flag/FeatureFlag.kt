package org.mobilenativefoundation.trails.shared.data.entity.flag

import kotlinx.serialization.Serializable

@Serializable
data class FeatureFlag(
    val id: Int,
    val key: String,
    val name: String,
    val description: String,
    val kind: Kind,
    val version: Int,
    val creationDate: Long,
    val variations: List<FeatureFlagVariation>,
    val tags: List<String>
) {
    enum class Kind {
        Boolean,
        Multivariate
    }
}