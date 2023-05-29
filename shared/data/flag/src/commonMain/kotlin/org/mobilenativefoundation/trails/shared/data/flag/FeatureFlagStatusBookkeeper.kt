package org.mobilenativefoundation.trails.shared.data.flag

import org.mobilenativefoundation.store.store5.Bookkeeper
import org.mobilenativefoundation.trails.shared.data.db.bookkeeper.FeatureFlagStatusHistoryQueries

class FeatureFlagStatusBookkeeper(
    private val featureFlagStatusHistoryQueries: FeatureFlagStatusHistoryQueries
) : Bookkeeper<FeatureFlagStatusKey> {
    override suspend fun clear(key: FeatureFlagStatusKey): Boolean {
        when (key) {
            is FeatureFlagStatusKey.Collection -> {
                featureFlagStatusHistoryQueries.removeKeyForUser(key.local(), key.userId)
            }

            is FeatureFlagStatusKey.Single -> {
                featureFlagStatusHistoryQueries.removeKeyForUser(key.local(), key.userId)
            }
        }

        return true
    }

    override suspend fun clearAll(): Boolean {
        featureFlagStatusHistoryQueries.clearAll()
        return true
    }

    override suspend fun setLastFailedSync(key: FeatureFlagStatusKey, timestamp: Long): Boolean {
        when (key) {
            is FeatureFlagStatusKey.Collection -> {
                featureFlagStatusHistoryQueries.insert(key.local(), key.userId, timestamp)
            }

            is FeatureFlagStatusKey.Single -> {
                featureFlagStatusHistoryQueries.insert(key.local(), key.userId, timestamp)
            }
        }
        return true
    }

    override suspend fun getLastFailedSync(key: FeatureFlagStatusKey): Long? {
        val history = when (key) {
            is FeatureFlagStatusKey.Collection -> {
                featureFlagStatusHistoryQueries.findOne(key.local(), key.userId)
            }

            is FeatureFlagStatusKey.Single -> {
                featureFlagStatusHistoryQueries.findOne(key.local(), key.userId)
            }
        }.executeAsOneOrNull()

        return history?.timestamp
    }

}