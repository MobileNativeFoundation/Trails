package org.mobilenativefoundation.trails.shared.mock.server.services

import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatus
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatuses
import org.mobilenativefoundation.trails.shared.mock.server.dao.FeatureFlagStatusDao

class FeatureFlagStatusServices(
    private val dao: FeatureFlagStatusDao
) {
    fun get(userId: Int, key: String): FeatureFlagStatus? = dao.findByKey(userId, key)
    fun get(userId: Int): FeatureFlagStatuses = FeatureFlagStatuses(dao.findAll(userId))

    fun update(userId: Int, key: String, featureFlagStatus: FeatureFlagStatus): Boolean =
        dao.upsert(userId, key, featureFlagStatus)
}