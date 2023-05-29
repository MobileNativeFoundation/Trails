package org.mobilenativefoundation.trails.shared.mock.server.db

import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagVariation

class MockVariations {
    val data: List<FeatureFlagVariation>
        get() = listOf(
            FeatureFlagVariation(id = 0, value = "CONTROL"),
            FeatureFlagVariation(id = 1, value = "V1"),
            FeatureFlagVariation(id = 2, value = "V2"),
            FeatureFlagVariation(id = 3, value = "V3"),
        )
}