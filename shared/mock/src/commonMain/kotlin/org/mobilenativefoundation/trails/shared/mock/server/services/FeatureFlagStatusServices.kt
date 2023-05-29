package org.mobilenativefoundation.trails.shared.mock.server.services

import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatus
import org.mobilenativefoundation.trails.shared.mock.server.dao.FeatureFlagStatusDao

class FeatureFlagStatusServices(
    private val dao: FeatureFlagStatusDao
) {
    fun get(key: String): FeatureFlagStatus? = dao.findByKey(key)
    fun get(): List<FeatureFlagStatus> = dao.findAll()

    fun update(key: String, featureFlagStatus: FeatureFlagStatus): Boolean =
        dao.upsert(key, featureFlagStatus)
}