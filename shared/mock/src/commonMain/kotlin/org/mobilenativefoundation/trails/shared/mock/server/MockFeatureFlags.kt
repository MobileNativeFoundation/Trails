package org.mobilenativefoundation.trails.shared.mock.server

import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlag
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatus
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagVariation
import org.mobilenativefoundation.trails.shared.data.entity.flag.Link
import org.mobilenativefoundation.trails.shared.data.entity.flag.Links
import kotlin.random.Random

class MockFeatureFlags(private val count: Int = 50) {

    val flags = flags().associateBy { it.key }
    val statuses = flags().map { generateFeatureFlagStatus(it) }.associateBy { it.key }

    private fun flags(): List<FeatureFlag> =
        (1 until count).map { generateFeatureFlag(it.toString()) }

    private fun generateLinks(key: String) = Links(
        self = Link(href = "/api/v1/flags/$key", type = "application/json")
    )


    private val variations = listOf(
        FeatureFlagVariation(id = 0, value = "CONTROL"),
        FeatureFlagVariation(id = 1, value = "V1"),
        FeatureFlagVariation(id = 2, value = "V2"),
        FeatureFlagVariation(id = 3, value = "V3"),
    )

    private fun generateFeatureFlag(key: String): FeatureFlag {
        val kind =
            if (Random.nextBoolean()) FeatureFlag.Kind.Boolean else FeatureFlag.Kind.Multivariate

        return FeatureFlag(
            id = key,
            key = key,
            name = "Flag $key",
            description = "Flag $key",
            kind = kind,
            version = Random.nextInt(1, 100),
            creationDate = System.currentTimeMillis() - Random.nextLong(1_000_000, 1_000_000_000),
            variations = if (kind == FeatureFlag.Kind.Boolean) listOf() else variations,
            tags = listOf("tag-1", "tag-2")
        )
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
                value = variations.random(),
                lastRequested = lastRequested,
                links = generateLinks(featureFlag.id)
            )
        }
    }
}
