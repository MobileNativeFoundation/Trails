package org.mobilenativefoundation.trails.android.wiring

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.mobilenativefoundation.trails.android.api.RealTrailsApi
import org.mobilenativefoundation.trails.android.common.wiring.AppScope
import org.mobilenativefoundation.trails.db.TrailsDb
import org.mobilenativefoundation.trails.shared.data.api.TrailsApi
import org.mobilenativefoundation.trails.shared.data.db.PageQueries
import org.mobilenativefoundation.trails.shared.data.db.PageSq
import org.mobilenativefoundation.trails.shared.data.db.PostOverviewQueries
import org.mobilenativefoundation.trails.shared.data.db.PostQueries
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

        val paramsAdapter = object : ColumnAdapter<PagingParams<String>, String> {
            override fun decode(databaseValue: String): PagingParams<String> {
                return serializer.decodeFromString(PagingParams.serializer(String.serializer()), databaseValue)
            }

            override fun encode(value: PagingParams<String>): String {
                return serializer.encodeToString(PagingParams.serializer(String.serializer()), value)
            }
        }

        val itemListAdapter = object : ColumnAdapter<Int, Long> {
            override fun decode(databaseValue: Long): Int {
                return databaseValue.toInt()
            }

            override fun encode(value: Int): Long {
                return value.toLong()
            }
        }

        val itemIdsAdapter = object : ColumnAdapter<List<String>, String> {
            override fun decode(databaseValue: String): List<String> {
                return serializer.decodeFromString(databaseValue)
            }

            override fun encode(value: List<String>): String {
                return serializer.encodeToString(value)
            }

        }

        return TrailsDb.invoke(
            driver = driver,
            pageSqAdapter = PageSq.Adapter(
                paramsAdapter = paramsAdapter,
                itemLimitAdapter = itemListAdapter,
                itemIdsAdapter = itemIdsAdapter,
                nextAdapter = paramsAdapter
            )
        )
    }

    @Provides
    fun providePageQueries(
        db: TrailsDb
    ): PageQueries = db.pageQueries

    @Provides
    fun providePostQueries(
        db: TrailsDb
    ): PostQueries = db.postQueries

    @Provides
    fun providePostOverviewQueries(
        db: TrailsDb
    ): PostOverviewQueries = db.postOverviewQueries

    @Provides
    fun provideTrailsApi(): TrailsApi = RealTrailsApi()
}
