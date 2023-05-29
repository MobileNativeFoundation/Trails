package org.mobilenativefoundation.trails.shared.mock.server.db

class MockDb {
    val featureFlags = MockFeatureFlags()
    val featureFlagStatuses = MockFeatureFlagStatuses(featureFlags.data)
}