package org.mobilenativefoundation.trails.android.feat.hike

import android.annotation.SuppressLint
import android.app.Application
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.android.gms.maps.model.TileProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.utils.io.*
import io.ktor.utils.io.jvm.javaio.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.combine
import org.mobilenativefoundation.trails.android.common.wiring.ComponentHolder
import org.mobilenativefoundation.trails.android.common.wiring.bindings
import org.mobilenativefoundation.trails.android.feat.trail.BitmapFileCache
import org.mobilenativefoundation.trails.android.feat.trail.BitmapKey
import org.mobilenativefoundation.trails.android.feat.trail.asTile
import org.mobilenativefoundation.trails.shared.data.entity.LatLng
import com.google.android.gms.maps.model.LatLng as GoogleMapsLatLng

interface MapManager {
    fun live(locationUpdates: Flow<LatLng>): Flow<GoogleMap>
}

@SuppressLint("MissingPermission")
class RealMapManager @AssistedInject constructor(
    application: Application,
    private val bitmapFileCache: BitmapFileCache,
    @Assisted map: GoogleMap
) : MapManager, ComponentHolder {

    override val component = application.bindings<HikeComponent.ParentBindings>().hikeComponentFactory().create()

    private val stateFlow = MutableStateFlow(map)

    private fun GoogleMap.configure() = apply {
        isMyLocationEnabled = true
        uiSettings.isMyLocationButtonEnabled = true
        setMapStyle()
        setTileProvider()
    }

    init {
        map.configure()
    }

    private fun setMap(map: GoogleMap) {
        stateFlow.value = map
    }

    private fun updateLocation(location: LatLng) {
        val map = stateFlow.value
        val latestLocation = GoogleMapsLatLng(location.latitude.toDouble(), location.longitude.toDouble())
        map.addMarker(MarkerOptions().position(latestLocation))
        setMap(map)
    }

    private fun GoogleMap.setMapStyle() {
        TODO()
    }

    private fun GoogleMap.setTileProvider() {
        val tileProvider = TileProvider { longitude, latitude, zoom ->
            bitmapFileCache.find(
                BitmapKey(
                    latitude.toFloat(),
                    longitude.toFloat(),
                    zoom
                )
            )?.asTile()
        }

        val tileOverlayOptions = TileOverlayOptions().tileProvider(tileProvider)
        this.addTileOverlay(tileOverlayOptions)
    }

    override fun live(locationUpdates: Flow<LatLng>): Flow<GoogleMap> = channelFlow {
        combine(locationUpdates, stateFlow) { location, map ->
            updateLocation(location)
            send(map)
        }
    }
}
