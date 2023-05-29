package org.mobilenativefoundation.trails.shared.mock.server.db

import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlag
import kotlin.random.Random

class MockFeatureFlags(private val count: Int = 50) {

    val map = flags().associateBy { it.key }.toMutableMap()
    val data = map.values.toMutableList()
    private val variations = MockVariations().data

    private fun flags(): List<FeatureFlag> =
        (1 until count).map { generateFeatureFlag(it.toString()) }

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
}
