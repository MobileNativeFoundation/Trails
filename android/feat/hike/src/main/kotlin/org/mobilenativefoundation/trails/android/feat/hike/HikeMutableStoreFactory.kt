package org.mobilenativefoundation.trails.android.feat.hike

import kotlinx.coroutines.flow.Flow
import org.mobilenativefoundation.store.store5.Bookkeeper
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.MutableStore
import org.mobilenativefoundation.store.store5.MutableStoreBuilder
import org.mobilenativefoundation.store.store5.SourceOfTruth
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

    private val fetcher = Fetcher.of<Int, Hike> { hikeId ->
        api.getHike(hikeId)
    }

    private val updater = Updater.by<Int, Hike, Hike>(
        post = { _, hike ->
            try {
                val latest = api.updateHike(hike)
                UpdaterResult.Success.Typed(latest)
            } catch (error: Throwable) {
                UpdaterResult.Error.Exception(error)
            }
        },
    )

    private val sourceOfTruth = SourceOfTruth.of<Int, Hike>(
        reader = { hikeId -> hikeDatabase.stream(hikeId) },
        writer = { _, hike -> hikeDatabase.write(hike) },
        delete = { hikeId -> hikeDatabase.clear(hikeId) },
        deleteAll = { hikeDatabase.clearAll() }
    )

    private val bookkeeper = Bookkeeper.by<Int>(
        getLastFailedSync = { hikeId -> bookkeepingDatabase.get(hikeId) },
        setLastFailedSync = { hikeId, timestamp -> bookkeepingDatabase.update(hikeId, timestamp) },
        clear = { hikeId -> bookkeepingDatabase.clear(hikeId) },
        clearAll = { bookkeepingDatabase.clearAll() }
    )

    private val validator = Validator.by<Hike> { hike ->
        hike.end == null
    }


    private val store = MutableStoreBuilder
        .from<Int, Hike, Hike, Hike>(
            fetcher = fetcher,
            sourceOfTruth = sourceOfTruth
        )
        .validator(validator)
        .build(
            updater = updater,
            bookkeeper = bookkeeper
        )


    fun create(): MutableStore<Int, Hike> = store
}


interface HikeDatabase {
    fun stream(hikeId: Int): Flow<Hike>
    fun write(hike: Hike)
    fun clear(hikeId: Int)
    fun clearAll()
}


interface BookkeepingDatabase {
    fun update(hikeId: Int, timestamp: Long): Boolean
    fun get(hikeId: Int): Long?
    fun clear(hikeId: Int): Boolean
    fun clearAll(): Boolean
}