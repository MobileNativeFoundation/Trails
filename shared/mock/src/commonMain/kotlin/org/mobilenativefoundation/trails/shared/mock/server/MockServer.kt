package org.mobilenativefoundation.trails.shared.mock.server

import org.mobilenativefoundation.trails.shared.mock.server.dao.FeatureFlagDao
import org.mobilenativefoundation.trails.shared.mock.server.dao.FeatureFlagStatusDao
import org.mobilenativefoundation.trails.shared.mock.server.db.MockDb
import org.mobilenativefoundation.trails.shared.mock.server.services.FeatureFlagServices
import org.mobilenativefoundation.trails.shared.mock.server.services.FeatureFlagStatusServices
import org.mobilenativefoundation.trails.shared.mock.server.services.TimelineServices

class MockServer {
    // Db
    private val db = MockDb()

    // DAO
    private val featureFlagDao = FeatureFlagDao(db.featureFlags)
    private val featureFlagStatusDao = FeatureFlagStatusDao(db.featureFlagStatuses)

    // Services
    val featureFlagServices = FeatureFlagServices(featureFlagDao)
    val featureFlagStatusServices = FeatureFlagStatusServices(featureFlagStatusDao)
    val timelineServices = TimelineServices()
}