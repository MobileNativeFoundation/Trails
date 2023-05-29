package org.mobilenativefoundation.trails.shared.mock.server.db

import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlag
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatus
import org.mobilenativefoundation.trails.shared.data.entity.flag.Link
import org.mobilenativefoundation.trails.shared.data.entity.flag.Links
import kotlin.random.Random

class MockFeatureFlagStatuses(flags: List<FeatureFlag>) {

    val data = mutableMapOf<Int, MutableList<FeatureFlagStatus>>().apply {
        (1..5).forEach { userId ->
            this[userId] = flags.map { generateFeatureFlagStatus(it) }.toMutableList()
        }
    }
    val map: Map<Int, Map<String, FeatureFlagStatus>>
        get() = data.entries.associate { (userId, featureFlagStatusesList) ->
            userId to featureFlagStatusesList.associateBy { featureFlagStatus -> featureFlagStatus.key }
        }

    private fun generateFeatureFlagStatus(featureFlag: FeatureFlag): FeatureFlagStatus {
        val lastRequested = System.currentTimeMillis() - Random.nextLong(1_000_000, 1_000_000_000)
        return if (featureFlag.kind == FeatureFlag.Kind.Boolean) {
            FeatureFlagStatus.Bool(
                key = featureFlag.key,
                value = Random.nextBoolean(),
                lastRequested = lastRequested,
                links = generateLinks(featureFlag.id)
            )
        } else {
            FeatureFlagStatus.Multivariate(
                key = featureFlag.key,
                value = MockVariations().data.random(),
                lastRequested = lastRequested,
                links = generateLinks(featureFlag.id)
            )
        }
    }

    private fun generateLinks(key: String) = Links(
        self = Link(href = "/api/v1/flags/$key", type = "application/json")
    )
}
