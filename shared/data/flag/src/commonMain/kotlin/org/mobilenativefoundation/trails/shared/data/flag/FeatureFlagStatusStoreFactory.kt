package org.mobilenativefoundation.trails.shared.data.flag

import org.mobilenativefoundation.store.cache5.Cache
import org.mobilenativefoundation.store.store5.Bookkeeper
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.MutableStore
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.Updater
import org.mobilenativefoundation.store.store5.UpdaterResult
import org.mobilenativefoundation.trails.shared.data.api.TrailsApi
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatusData

class FeatureFlagStatusStoreFactory(
    private val api: TrailsApi
) {
    fun create(): MutableStore<FeatureFlagStatusKey, FeatureFlagStatusData> =
        StoreBuilder.from(
            fetcher = createFetcher(),
            sourceOfTruth = createSourceOfTruth(),
            memoryCache = createMemoryCache()
        ).toMutableStoreBuilder<FeatureFlagStatusData, FeatureFlagStatusData>().build(
            updater = createUpdater(),
            bookkeeper = createBookkeeper()
        )

    private fun createUpdater(): Updater<FeatureFlagStatusKey, FeatureFlagStatusData, Boolean> =
        Updater.by(
            post = { key, data ->
                when {
                    key is FeatureFlagStatusKey.Single && data is FeatureFlagStatusData.Single -> {
                        val result = api.updateFeatureFlagStatus(key.userId, key.key, data.item)
                        if (result) {
                            UpdaterResult.Success.Typed(true)
                        } else {
                            UpdaterResult.Error.Message("Server failure")
                        }
                    }

                    else -> UpdaterResult.Error.Message("Unsupported")
                }
            }
        )

    private fun createFetcher(): Fetcher<FeatureFlagStatusKey, FeatureFlagStatusData> =
        Fetcher.of { key ->
            when (key) {
                is FeatureFlagStatusKey.Collection -> {
                    val response = api.getFeatureFlagStatuses(key.userId)
                    FeatureFlagStatusData.Collection(response.items)
                }

                is FeatureFlagStatusKey.Single -> {
                    val response = api.getFeatureFlagStatus(key.userId, key.key)
                    FeatureFlagStatusData.Single(response)
                }
            }
        }

    private fun createSourceOfTruth(): SourceOfTruth<FeatureFlagStatusKey, FeatureFlagStatusData> {
        TODO()
    }

    private fun createMemoryCache(): Cache<FeatureFlagStatusKey, FeatureFlagStatusData> {
        TODO()
    }

    private fun createBookkeeper(): Bookkeeper<FeatureFlagStatusKey> {
        TODO()
    }

}