package org.mobilenativefoundation.trails.android.feat.hike

import com.google.android.gms.maps.GoogleMap
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.mobilenativefoundation.store.store5.ExperimentalStoreApi
import org.mobilenativefoundation.store.store5.MutableStore
import org.mobilenativefoundation.store.store5.StoreWriteRequest
import org.mobilenativefoundation.store.store5.StoreWriteResponse
import org.mobilenativefoundation.trails.shared.data.api.TrailsApi
import org.mobilenativefoundation.trails.shared.data.entity.Hike
import org.mobilenativefoundation.trails.shared.data.entity.LatLng
import org.mobilenativefoundation.trails.shared.data.entity.User

// TODO()
typealias Directions = List<String>


data class Waypoint(
    val name: String,
    val location: LatLng
)

data class DistanceAway(
    val feet: Int
)

data class Nav(
    val nextWaypoint: Waypoint,
    val directions: Directions,
    val eta: Long,
    val distanceAway: DistanceAway
)

interface HikeManager {
    fun nav(): Flow<Nav>
    fun map(): Flow<GoogleMap>
}

@Suppress("UNCHECKED_CAST")
@OptIn(ExperimentalStoreApi::class)
class RealHikeManager @AssistedInject constructor(
    scope: CoroutineScope,
    private val api: TrailsApi,
    private val mutableStore: MutableStore<String, Hike>,
    private val locationManager: LocationManager,
    private val mapManager: MapManager,
    private val user: User,
    @Assisted private val trailId: String
) : HikeManager {

    private val hikeStateFlow: MutableStateFlow<Hike?> = MutableStateFlow(null)
    private val navStateFlow: MutableStateFlow<Nav?> = MutableStateFlow(null)
    private val mapStateFlow: MutableStateFlow<GoogleMap?> = MutableStateFlow(null)

    init {
        scope.launch {
            start()
            track()
        }
    }

    private suspend fun start() {
        hikeStateFlow.value = api.startHike(user.id, trailId)
    }

    // TODO(): Make thread safe
    private suspend fun track() {

        if (hikeStateFlow.value == null) {
            return
        }

        val locationUpdates = locationManager.streamLocation()
        val mapUpdates = mapManager.live(locationUpdates)

        combine(locationUpdates, mapUpdates) { location, map ->
            val writeRequest = StoreWriteRequest.of<String, Hike, Hike>(
                key = trailId,
                value = hikeStateFlow.value!!.copy(path = hikeStateFlow.value!!.path + location)
            )

            val response = mutableStore.write(writeRequest)
            if (response is StoreWriteResponse.Success.Typed<*>) {
                val hike = (response as StoreWriteResponse.Success.Typed<Hike>).value
                hikeStateFlow.value = hike

                val latestLocation = hike.path.last()

                val nextWaypoint = findNextWaypoint(latestLocation)
                val directions = getDirections(latestLocation, nextWaypoint.location)
                val eta = calculateEta(latestLocation, nextWaypoint.location)
                val distanceAway = calculateDistanceAway(latestLocation, nextWaypoint.location)

                navStateFlow.value = Nav(
                    nextWaypoint = nextWaypoint,
                    directions = directions,
                    eta = eta,
                    distanceAway = distanceAway
                )

                mapStateFlow.value = map
            }
        }
    }

    override fun nav(): Flow<Nav> = navStateFlow.filterNotNull()
    override fun map(): Flow<GoogleMap> = mapStateFlow.filterNotNull()
    private fun findNextWaypoint(latestLocation: LatLng): Waypoint = TODO()
    private fun getDirections(latestLocation: LatLng, nextWaypoint: LatLng): Directions = TODO()
    private fun calculateEta(latestLocation: LatLng, nextWaypoint: LatLng): Long = TODO()
    private fun calculateDistanceAway(latestLocation: LatLng, nextWaypoint: LatLng): DistanceAway = TODO()
}