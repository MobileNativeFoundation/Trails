package org.mobilenativefoundation.trails.shared.mock.server.dao

import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatus
import org.mobilenativefoundation.trails.shared.mock.server.db.MockFeatureFlagStatuses

class FeatureFlagStatusDao(
    private val db: MockFeatureFlagStatuses
) {
    fun findByKey(key: String): FeatureFlagStatus? = db.map[key]
    fun findAll(): List<FeatureFlagStatus> = db.data

    fun upsert(key: String, featureFlagStatus: FeatureFlagStatus): Boolean {
        val index = db.data.indexOfFirst { it.key == key }
        if (index >= 0) {
            db.data[index] = featureFlagStatus
        } else {
            db.data.add(featureFlagStatus)
        }
        db.map[key] = featureFlagStatus

        return true
    }
}