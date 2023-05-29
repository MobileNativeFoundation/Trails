package org.mobilenativefoundation.trails.shared.data.flag

import kotlinx.coroutines.flow.flow
import org.mobilenativefoundation.store.store5.Converter
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.MutableStore
import org.mobilenativefoundation.store.store5.MutableStoreBuilder
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.Updater
import org.mobilenativefoundation.trails.shared.data.api.TrailsApi
import org.mobilenativefoundation.trails.shared.data.db.FeatureFlagQueries
import org.mobilenativefoundation.trails.shared.data.db.FeatureFlagSq
import org.mobilenativefoundation.trails.shared.data.db.translations.local
import org.mobilenativefoundation.trails.shared.data.db.translations.output
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlag

class FeatureFlagStoreFactory(
    private val api: TrailsApi,
    private val featureFlagQueries: FeatureFlagQueries
) {
    fun create(): MutableStore<String, FeatureFlag> =
        MutableStoreBuilder.from<String, FeatureFlag, FeatureFlag, FeatureFlagSq>(
            fetcher = createFetcher(),
            sourceOfTruth = createSourceOfTruth(),
        ).converter(createConverter()).build(createUpdater())


    private fun createConverter(): Converter<FeatureFlag, FeatureFlag, FeatureFlagSq> =
        Converter.Builder<FeatureFlag, FeatureFlag, FeatureFlagSq>()
            .fromOutputToLocal { it.local() }
            .fromLocalToOutput { it.output() }
            .fromNetworkToOutput { it }
            .build()

    private fun createUpdater(): Updater<String, FeatureFlag, Boolean> {
        throw UnsupportedOperationException()
    }

    private fun createFetcher(): Fetcher<String, FeatureFlag> = Fetcher.of { key ->
        api.getFeatureFlag(key)
    }

    private fun createSourceOfTruth(): SourceOfTruth<String, FeatureFlagSq> = SourceOfTruth.of(
        reader = { key ->
            flow {
                emit(featureFlagQueries.findByKey(key).executeAsOneOrNull())
            }
        },
        writer = { key, featureFlag ->
            featureFlagQueries.insert(
                key = key,
                id = featureFlag.id,
                name = featureFlag.name,
                description = featureFlag.description,
                kind = featureFlag.kind,
                version = featureFlag.version,
                creationDate = featureFlag.creationDate,
                variations = featureFlag.variations,
                tags = featureFlag.tags
            )
        }
    )
}