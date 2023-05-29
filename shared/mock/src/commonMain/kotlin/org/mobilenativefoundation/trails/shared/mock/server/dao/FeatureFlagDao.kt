package org.mobilenativefoundation.trails.shared.mock.server.dao

import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlag
import org.mobilenativefoundation.trails.shared.mock.server.db.MockFeatureFlags

class FeatureFlagDao(
    private val db: MockFeatureFlags
) {
    fun findByKey(key: String): FeatureFlag? = db.map[key]
    fun findAll(): List<FeatureFlag> = db.data

    fun upsert(key: String, featureFlag: FeatureFlag): Boolean {
        val index = db.data.indexOfFirst { it.key == key }
        if (index >= 0) {
            db.data[index] = featureFlag
        } else {
            db.data.add(featureFlag)
        }
        db.map[key] = featureFlag

        return true
    }
}