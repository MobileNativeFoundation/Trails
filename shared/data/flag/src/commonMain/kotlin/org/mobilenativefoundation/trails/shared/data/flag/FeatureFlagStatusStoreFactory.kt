package org.mobilenativefoundation.trails.shared.data.flag

import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.mobilenativefoundation.store.cache5.Cache
import org.mobilenativefoundation.store.store5.Bookkeeper
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.MutableStore
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.Updater
import org.mobilenativefoundation.store.store5.UpdaterResult
import org.mobilenativefoundation.trails.shared.data.api.TrailsApi
import org.mobilenativefoundation.trails.shared.data.db.FeatureFlagStatusQueries
import org.mobilenativefoundation.trails.shared.data.db.bookkeeper.FeatureFlagStatusHistoryQueries
import org.mobilenativefoundation.trails.shared.data.db.translations.local
import org.mobilenativefoundation.trails.shared.data.db.translations.output
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatus
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatusData

class FeatureFlagStatusStoreFactory(
    private val api: TrailsApi,
    private val featureFlagStatusQueries: FeatureFlagStatusQueries,
    private val featureFlagStatusHistoryQueries: FeatureFlagStatusHistoryQueries,
    private val serializer: Json
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

    private fun createSourceOfTruth(): SourceOfTruth<FeatureFlagStatusKey, FeatureFlagStatusData> =
        SourceOfTruth.of(
            reader = { key ->
                flow {
                    when (key) {
                        is FeatureFlagStatusKey.Collection -> {
                            val statuses =
                                featureFlagStatusQueries.findByUser(key.userId).executeAsList()
                            val output = statuses.map { it.output(serializer) }
                            emit(FeatureFlagStatusData.Collection(output))
                        }

                        is FeatureFlagStatusKey.Single -> {
                            val status = featureFlagStatusQueries.findOne(key.key, key.userId)
                                .executeAsOneOrNull()
                            val output = status?.output(serializer)

                            if (output != null) {
                                emit(FeatureFlagStatusData.Single(output))
                            } else {
                                emit(null)
                            }
                        }
                    }
                }
            },
            writer = { key, data ->
                fun writeItem(key: String, userId: Int, item: FeatureFlagStatus) {
                    when (item) {
                        is FeatureFlagStatus.Bool -> {
                            val local = item.local(userId = userId)
                            featureFlagStatusQueries.insert(
                                key = local.key,
                                userId = local.userId,
                                lastRequested = local.lastRequested,
                                links = local.links,
                                type = local.type,
                                value_ = local.value_
                            )
                        }

                        is FeatureFlagStatus.Multivariate -> {
                            val local = item.local(userId = userId, serializer = serializer)
                            featureFlagStatusQueries.insert(
                                key = local.key,
                                userId = local.userId,
                                lastRequested = local.lastRequested,
                                links = local.links,
                                type = local.type,
                                value_ = local.value_
                            )
                        }
                    }
                }

                when {
                    key is FeatureFlagStatusKey.Single && data is FeatureFlagStatusData.Single -> {
                        writeItem(key.key, key.userId, data.item)
                    }

                    key is FeatureFlagStatusKey.Collection && data is FeatureFlagStatusData.Collection -> {
                        data.items.forEach { item ->
                            writeItem(item.key, key.userId, item)
                        }

                    }

                    else -> {
                        throw UnsupportedOperationException()
                    }
                }
            }
        )

    private fun createMemoryCache(): Cache<FeatureFlagStatusKey, FeatureFlagStatusData> =
        FeatureFlagStatusMemoryCache()

    private fun createBookkeeper(): Bookkeeper<FeatureFlagStatusKey> =
        FeatureFlagStatusBookkeeper(featureFlagStatusHistoryQueries)
}