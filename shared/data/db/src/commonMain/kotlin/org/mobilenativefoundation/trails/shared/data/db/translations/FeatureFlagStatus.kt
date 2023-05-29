package org.mobilenativefoundation.trails.shared.data.db.translations

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.mobilenativefoundation.trails.shared.data.db.FeatureFlagStatusSq
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatus
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagVariation

fun FeatureFlagStatus.Bool.local(userId: Int) = FeatureFlagStatusSq(
    key = key,
    userId = userId,
    lastRequested,
    links,
    type = "bool",
    value_ = value.toString()
)

fun FeatureFlagStatus.Multivariate.local(userId: Int, serializer: Json) = FeatureFlagStatusSq(
    key = key,
    userId = userId,
    lastRequested = lastRequested,
    links = links,
    type = "multivariate",
    value_ = serializer.encodeToString(value)
)

fun FeatureFlagStatusSq.output(serializer: Json): FeatureFlagStatus = when (this.type) {
    "bool" -> {
        FeatureFlagStatus.Bool(
            key = key,
            value = value_.toBoolean(),
            lastRequested = lastRequested,
            links = links
        )
    }

    "multivariate" -> {
        FeatureFlagStatus.Multivariate(
            key = key,
            value = serializer.decodeFromString(FeatureFlagVariation.serializer(), value_),
            lastRequested = lastRequested,
            links = links
        )
    }

    else -> throw UnsupportedOperationException()
}