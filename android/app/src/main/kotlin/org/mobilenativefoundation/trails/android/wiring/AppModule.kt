package org.mobilenativefoundation.trails.android.wiring

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.mobilenativefoundation.trails.android.api.MockTrailsApi
import org.mobilenativefoundation.trails.android.common.wiring.AppScope
import org.mobilenativefoundation.trails.db.TrailsDb
import org.mobilenativefoundation.trails.shared.data.api.TrailsApi
import org.mobilenativefoundation.trails.shared.data.db.PostOverviewSq
import org.mobilenativefoundation.trails.shared.data.db.TimelinePagingDataSq
import org.mobilenativefoundation.trails.shared.data.db.TimelinePagingParamsSq
import org.mobilenativefoundation.trails.shared.data.db.TimelinePostOverview
import org.mobilenativefoundation.trails.shared.data.entity.paging.TimelinePagingParams
import org.mobilenativefoundation.trails.shared.paging.core.PagingParams

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

        return TrailsDb.invoke(
            driver = driver,
            postOverviewSqAdapter = postOverviewSqAdapter,
            timelinePagingDataSqAdapter = timelinePagingDataSqAdapter,
            timelinePagingParamsSqAdapter = timelinePagingParamsSqAdapter,
            timelinePostOverviewAdapter = timelinePostOverviewAdapter
        )
    }

    @Provides
    fun provideTrailsApi(): TrailsApi = MockTrailsApi()
}