package org.mobilenativefoundation.trails.android.feat.hike

import kotlinx.coroutines.flow.Flow
import org.mobilenativefoundation.store.store5.Bookkeeper
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.MutableStore
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.Updater
import org.mobilenativefoundation.store.store5.UpdaterResult
import org.mobilenativefoundation.store.store5.Validator
import org.mobilenativefoundation.trails.shared.data.api.TrailsApi
import org.mobilenativefoundation.trails.shared.data.entity.Hike
import javax.inject.Inject


class HikeMutableStoreFactory @Inject constructor(
    private val api: TrailsApi,
    private val hikeDatabase: HikeDatabase,
    private val bookkeepingDatabase: BookkeepingDatabase
) {

    private val fetcher = Fetcher.of<String, Hike> { hikeId ->
        api.getHike(hikeId)
    }

    private val updater = Updater.by<String, Hike, Hike>(
        post = { _, hike ->
            try {
                val latest = api.updateHike(hike)
                UpdaterResult.Success.Typed(latest)
            } catch (error: Throwable) {
                UpdaterResult.Error.Exception(error)
            }
        },
    )

    private val sourceOfTruth = SourceOfTruth.of<String, Hike>(
        reader = { hikeId -> hikeDatabase.stream(hikeId) },
        writer = { _, hike -> hikeDatabase.write(hike) },
        delete = { hikeId -> hikeDatabase.clear(hikeId) },
        deleteAll = { hikeDatabase.clearAll() }
    )

    private val bookkeeper = Bookkeeper.by<String>(
        getLastFailedSync = { hikeId -> bookkeepingDatabase.get(hikeId) },
        setLastFailedSync = { hikeId, timestamp -> bookkeepingDatabase.update(hikeId, timestamp) },
        clear = { hikeId -> bookkeepingDatabase.clear(hikeId) },
        clearAll = { bookkeepingDatabase.clearAll() }
    )

    private val validator = Validator.by<Hike> { hike ->
        hike.end == null
    }


    private val store = StoreBuilder
        .from<String, Hike, Hike, Hike>(
            fetcher = fetcher,
            sourceOfTruth = sourceOfTruth
        )
        .validator(validator)
        .build(
            updater = updater,
            bookkeeper = bookkeeper
        )


    fun create(): MutableStore<String, Hike> = store
}


interface HikeDatabase {
    fun stream(hikeId: String): Flow<Hike>
    fun write(hike: Hike)
    fun clear(hikeId: String)
    fun clearAll()
}


interface BookkeepingDatabase {
    fun update(hikeId: String, timestamp: Long): Boolean
    fun get(hikeId: String): Long?
    fun clear(hikeId: String): Boolean
    fun clearAll(): Boolean
}