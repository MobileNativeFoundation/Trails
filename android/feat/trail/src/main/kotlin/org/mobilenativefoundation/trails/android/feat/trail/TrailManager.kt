package org.mobilenativefoundation.trails.android.feat.trail


import org.mobilenativefoundation.store.store5.StoreReadRequest
import org.mobilenativefoundation.trails.shared.data.entity.LatLng
import org.mobilenativefoundation.trails.shared.data.entity.Trail
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.sinh
import kotlin.math.tan


data class TileCoordinates(val x: Int, val y: Int)


fun getLatLngFromTile(x: Int, y: Int, zoom: Int): LatLng {
    val n = 1 shl zoom
    val longitude = x / n * 360.0 - 180.0
    val latitudeRad = atan(sinh(Math.PI * (1 - 2 * y / n)))
    val latitude = Math.toDegrees(latitudeRad)

    return LatLng(latitude.toFloat(), longitude.toFloat())
}

fun getTileCoordinates(location: LatLng, zoom: Int): TileCoordinates {
    val latRad = Math.toRadians(location.latitude.toDouble())
    val n = 1 shl zoom
    val x = ((location.longitude.toDouble() + 180) / 360 * n).toInt()
    val y = ((1 - ln(tan(latRad) + (1 / cos(latRad))) / Math.PI) / 2 * n).toInt()

    return TileCoordinates(x, y)
}

class RealTrailManager(private val bitmapStore: BitmapStore) {

    val trail: Trail = TODO()
    suspend fun makeAvailableOffline() {
        downloadTiles()
        downloadDirections()
    }

    private suspend fun downloadTiles() {
        val minZoom = 13
        val maxZoom = 16

        for (zoom in minZoom..maxZoom) {
            val bottomLeftTile = getTileCoordinates(trail.bounds.southwest, zoom)
            val topRightTile = getTileCoordinates(trail.bounds.northeast, zoom)

            for (x in bottomLeftTile.x..topRightTile.x) {
                for (y in bottomLeftTile.y..topRightTile.y) {
                    val tileLatLng = getLatLngFromTile(x, y, zoom)
                    val key = BitmapKey(tileLatLng.latitude, tileLatLng.longitude, zoom)
                    val request = StoreReadRequest.fresh(key)
                    bitmapStore.stream(request)
                }
            }
        }
    }

    private suspend fun downloadDirections() {}
}


