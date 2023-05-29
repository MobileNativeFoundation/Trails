package org.mobilenativefoundation.trails.shared.mock.server.dao

import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatus
import org.mobilenativefoundation.trails.shared.mock.server.db.MockFeatureFlagStatuses

class FeatureFlagStatusDao(
    private val db: MockFeatureFlagStatuses
) {
    fun findByKey(userId: Int, key: String): FeatureFlagStatus? = db.map[userId]?.get(key)
    fun findAll(userId: Int): List<FeatureFlagStatus> = db.data[userId] ?: listOf()

    fun upsert(userId: Int, key: String, featureFlagStatus: FeatureFlagStatus): Boolean {
        val index = db.data[userId]?.indexOfFirst { it.key == key }

        when {
            index != null && index >= 0 -> {
                db.data[userId]?.let {
                    it[index] = featureFlagStatus
                }
            }

            index != null && index < 0 -> {
                db.data[userId]?.let {
                    it.add(featureFlagStatus)
                }
            }

            else -> {
                db.data[userId] = mutableListOf(featureFlagStatus)
            }
        }
        return true
    }
}