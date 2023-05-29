package org.mobilenativefoundation.trails.shared.mock.server.services

import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlag
import org.mobilenativefoundation.trails.shared.mock.server.dao.FeatureFlagDao
import java.awt.Taskbar.Feature

class FeatureFlagServices(
    private val dao: FeatureFlagDao
) {
    fun get(key: String): FeatureFlag? = dao.findByKey(key)

    fun put(key: String, featureFlag: FeatureFlag) = dao.upsert(key, featureFlag)
}