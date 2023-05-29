package org.mobilenativefoundation.trails.android.wiring

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.mobilenativefoundation.store.store5.MutableStore
import org.mobilenativefoundation.trails.android.api.MockTrailsApi
import org.mobilenativefoundation.trails.android.common.wiring.AppScope
import org.mobilenativefoundation.trails.android.common.wiring.Names.FEATURE_FLAG_STATUS_STORE
import org.mobilenativefoundation.trails.android.common.wiring.Names.FEATURE_FLAG_STORE
import org.mobilenativefoundation.trails.db.TrailsDb
import org.mobilenativefoundation.trails.shared.data.api.TrailsApi
import org.mobilenativefoundation.trails.shared.data.db.FeatureFlagSq
import org.mobilenativefoundation.trails.shared.data.db.FeatureFlagStatusSq
import org.mobilenativefoundation.trails.shared.data.db.PostOverviewSq
import org.mobilenativefoundation.trails.shared.data.db.TimelinePagingDataSq
import org.mobilenativefoundation.trails.shared.data.db.TimelinePagingParamsSq
import org.mobilenativefoundation.trails.shared.data.db.TimelinePostOverview
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlag
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatusData
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagVariation
import org.mobilenativefoundation.trails.shared.data.entity.flag.Links
import org.mobilenativefoundation.trails.shared.data.entity.paging.TimelinePagingParams
import org.mobilenativefoundation.trails.shared.data.flag.FeatureFlagStatusKey
import org.mobilenativefoundation.trails.shared.data.flag.FeatureFlagStatusStoreFactory
import org.mobilenativefoundation.trails.shared.data.flag.FeatureFlagStoreFactory
import org.mobilenativefoundation.trails.shared.paging.core.PagingParams
import javax.inject.Named

@Module
@ContributesTo(AppScope::class)
object AppModule {

    @Provides
    fun provideSerializer(): Json = Json

    @Provides
    fun provideTrailsDb(
        serializer: Json,
        driver: SqlDriver
    ): TrailsDb {

        val paramsAdapter = object : ColumnAdapter<TimelinePagingParams, String> {
            override fun decode(databaseValue: String): TimelinePagingParams =
                serializer.decodeFromString(databaseValue)

            override fun encode(value: TimelinePagingParams): String =
                serializer.encodeToString(value)

        }

        val intAdapter = object : ColumnAdapter<Int, Long> {
            override fun decode(databaseValue: Long): Int = databaseValue.toInt()

            override fun encode(value: Int): Long = value.toLong()
        }

        val typeAdapter = object : ColumnAdapter<PagingParams.Type, String> {
            override fun decode(databaseValue: String): PagingParams.Type =
                PagingParams.Type.lookup(databaseValue)

            override fun encode(value: PagingParams.Type): String = value.name

        }

        val tagsAdapter = object : ColumnAdapter<List<String>, String> {
            override fun decode(databaseValue: String): List<String> =
                serializer.decodeFromString(databaseValue)

            override fun encode(value: List<String>): String = serializer.encodeToString(value)

        }

        val kindAdapter = object : ColumnAdapter<FeatureFlag.Kind, String> {
            override fun decode(databaseValue: String): FeatureFlag.Kind =
                FeatureFlag.Kind.valueOf(databaseValue)

            override fun encode(value: FeatureFlag.Kind): String = value.name

        }

        val longAdapter = object : ColumnAdapter<Long, Long> {
            override fun decode(databaseValue: Long): Long = databaseValue
            override fun encode(value: Long): Long = value
        }

        val variationsAdapter = object : ColumnAdapter<List<FeatureFlagVariation>, String> {
            override fun decode(databaseValue: String): List<FeatureFlagVariation> =
                serializer.decodeFromString(databaseValue)

            override fun encode(value: List<FeatureFlagVariation>): String =
                serializer.encodeToString(value)

        }

        val linksAdapter = object : ColumnAdapter<Links, String> {
            override fun decode(databaseValue: String): Links =
                serializer.decodeFromString(databaseValue)

            override fun encode(value: Links): String = serializer.encodeToString(value)

        }

        val timelinePagingDataSqAdapter = TimelinePagingDataSq.Adapter(
            idAdapter = intAdapter,
            paramsIdAdapter = intAdapter,
            nextAdapter = paramsAdapter
        )

        val postOverviewSqAdapter = PostOverviewSq.Adapter(
            idAdapter = intAdapter,
            userIdAdapter = intAdapter,
            hikeIdAdapter = intAdapter
        )

        val timelinePagingParamsSqAdapter = TimelinePagingParamsSq.Adapter(
            idAdapter = intAdapter,
            userIdAdapter = intAdapter,
            loadSizeAdapter = intAdapter,
            afterAdapter = intAdapter,
            typeAdapter = typeAdapter
        )

        val timelinePostOverviewAdapter = TimelinePostOverview.Adapter(
            idAdapter = intAdapter,
            pageIdAdapter = intAdapter,
            postOverviewIdAdapter = intAdapter
        )

        val featureFlagSqAdapter = FeatureFlagSq.Adapter(
            kindAdapter = kindAdapter,
            versionAdapter = intAdapter,
            creationDateAdapter = longAdapter,
            variationsAdapter = variationsAdapter,
            tagsAdapter = tagsAdapter
        )

        val featureFlagStatusSqAdapter = FeatureFlagStatusSq.Adapter(
            userIdAdapter = intAdapter,
            lastRequestedAdapter = longAdapter,
            linksAdapter = linksAdapter
        )

        return TrailsDb.invoke(
            driver = driver,
            postOverviewSqAdapter = postOverviewSqAdapter,
            timelinePagingDataSqAdapter = timelinePagingDataSqAdapter,
            timelinePagingParamsSqAdapter = timelinePagingParamsSqAdapter,
            timelinePostOverviewAdapter = timelinePostOverviewAdapter,
            featureFlagSqAdapter = featureFlagSqAdapter,
            featureFlagStatusSqAdapter = featureFlagStatusSqAdapter
        )
    }

    @Provides
    fun provideTrailsApi(): TrailsApi = MockTrailsApi()

    @Provides
    @Named(FEATURE_FLAG_STORE)
    fun provideFeatureFlagStore(
        api: TrailsApi,
        db: TrailsDb
    ): MutableStore<String, FeatureFlag> {
        val featureFlagQueries = db.featureFlagQueries
        val factory = FeatureFlagStoreFactory(api, featureFlagQueries)
        return factory.create()
    }

    @Provides
    @Named(FEATURE_FLAG_STATUS_STORE)
    fun provideFeatureFlagStatusStore(
        api: TrailsApi,
        db: TrailsDb
    ): MutableStore<FeatureFlagStatusKey, FeatureFlagStatusData> {
        val factory = FeatureFlagStatusStoreFactory(api)
        return factory.create()
    }
}