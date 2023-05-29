package org.mobilenativefoundation.trails.shared.data.entity.flag

import kotlinx.serialization.Serializable
import org.mobilenativefoundation.store.cache5.Identifiable

@Serializable
data class FeatureFlag(
    val key: String,
    override val id: String = key,
    val name: String,
    val description: String,
    val kind: Kind,
    val version: Int,
    val creationDate: Long,
    val variations: List<FeatureFlagVariation>,
    val tags: List<String>
) : Identifiable<String> {
    enum class Kind {
        Boolean,
        Multivariate
    }
}