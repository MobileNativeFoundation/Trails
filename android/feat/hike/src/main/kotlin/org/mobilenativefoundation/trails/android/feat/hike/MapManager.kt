package org.mobilenativefoundation.trails.android.feat.hike

import com.google.android.gms.maps.GoogleMap
import kotlinx.coroutines.flow.Flow
import org.mobilenativefoundation.trails.shared.data.entity.LatLng

interface MapManager {
    fun live(locationUpdates: Flow<LatLng>): Flow<GoogleMap>
}

//@SuppressLint("MissingPermission")
//class RealMapManager @AssistedInject constructor(
//    application: Application,
//    private val bitmapFileCache: BitmapFileCache,
//    @Assisted map: GoogleMap
//) : MapManager, ComponentHolder {
//
//    override val component = application.bindings<HikeComponent.ParentBindings>().hikeComponentFactory().create()
//
//    private val stateFlow = MutableStateFlow(map)
//
//    private fun GoogleMap.configure() = apply {
//        isMyLocationEnabled = true
//        uiSettings.isMyLocationButtonEnabled = true
//        setMapStyle()
//        setTileProvider()
//    }
//
//    init {
//        map.configure()
//    }
//
//    private fun setMap(map: GoogleMap) {
//        stateFlow.value = map
//    }
//
//    private fun updateLocation(location: LatLng) {
//        val map = stateFlow.value
//        val latestLocation = GoogleMapsLatLng(location.latitude.toDouble(), location.longitude.toDouble())
//        map.addMarker(MarkerOptions().position(latestLocation))
//        setMap(map)
//    }
//
//    private fun GoogleMap.setMapStyle() {
//        TODO()
//    }
//
//    private fun GoogleMap.setTileProvider() {
//        val tileProvider = TileProvider { longitude, latitude, zoom ->
//            bitmapFileCache.find(
//                BitmapKey(
//                    latitude.toFloat(),
//                    longitude.toFloat(),
//                    zoom
//                )
//            )?.asTile()
//        }
//
//        val tileOverlayOptions = TileOverlayOptions().tileProvider(tileProvider)
//        this.addTileOverlay(tileOverlayOptions)
//    }
//
//    override fun live(locationUpdates: Flow<LatLng>): Flow<GoogleMap> = channelFlow {
//        combine(locationUpdates, stateFlow) { location, map ->
//            updateLocation(location)
//            send(map)
//        }
//    }
//}
