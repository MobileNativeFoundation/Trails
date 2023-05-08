package org.mobilenativefoundation.trails.android.feat.trail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.android.gms.maps.model.Tile
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.utils.io.*
import io.ktor.utils.io.jvm.javaio.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.trails.shared.data.entity.Hike
import java.io.ByteArrayOutputStream
import javax.inject.Inject


data class BitmapKey(
    val latitude: Float,
    val longitude: Float,
    val zoom: Int
)


typealias TileStore = Store<BitmapKey, Tile>

const val TILE_STORE = "TILE_STORE"

interface MapsStaticApi {
    suspend fun getBitmap(latitude: Float, longitude: Float, zoom: Int): Bitmap?
}


fun String.param(key: String, value: Any) = "$this&$key=$value"


class TileUrlBuilder(
    latitude: Float,
    longitude: Float,
    zoom: Int
) {
    private var url: String = "https://maps.googleapis.com/maps/api/staticmap?"
        .param("center", "$latitude,$longitude")
        .param("zoom", zoom)

    private var size: Int = 256
    private var scale: Int = 1
    private var format: String = "png"
    private var mapType: String = "terrain"

    fun size(value: Int): TileUrlBuilder {
        this.size = value
        return this
    }

    fun scale(value: Int): TileUrlBuilder {
        this.scale = value
        return this
    }

    fun format(value: String): TileUrlBuilder {
        this.format = value
        return this
    }

    fun mapType(value: String): TileUrlBuilder {
        this.mapType = value
        return this
    }

    fun build() = url
        .param("size", size)
        .param("scale", scale)
        .param("format", format)
        .param("maptype", mapType)
}


class RealMapsStaticApi @Inject constructor(
    private val apiKey: String,
) : MapsStaticApi {

    @OptIn(InternalAPI::class)
    override suspend fun getBitmap(latitude: Float, longitude: Float, zoom: Int): Bitmap? =
        withContext(Dispatchers.IO) {
            val client = HttpClient(CIO)

            val bitmapUrlBuilder = TileUrlBuilder(latitude, longitude, zoom)

            try {
                val response: HttpResponse = client.get(bitmapUrlBuilder.build())
                if (response.status.isSuccess()) {
                    val channel: ByteReadChannel = response.content
                    channel.toInputStream().use { inputStream ->
                        return@withContext BitmapFactory.decodeStream(inputStream)
                    }
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            } finally {
                client.close()
            }
        }
}


typealias BitmapStore = Store<BitmapKey, Bitmap>

class BitmapStoreFactory @Inject constructor(
    private val api: MapsStaticApi,
    private val fileCache: BitmapFileCache,
) {
    private val fetcher = Fetcher.of<BitmapKey, Bitmap> { (latitude, longitude, zoom) ->
        api.getBitmap(latitude, longitude, zoom) ?: throw Exception()
    }

    private val sourceOfTruth = SourceOfTruth.of<BitmapKey, Bitmap>(
        reader = { key ->
            flow {
                val bitmap = fileCache.find(key)
                if (bitmap != null) {
                    emit(bitmap)
                }
            }
        },
        writer = { key, bitmap -> fileCache.put(key, bitmap) },
        delete = { key -> fileCache.clear(key) },
        deleteAll = null
    )

    private val store = StoreBuilder.from<BitmapKey, Bitmap, Bitmap, Bitmap>(
        fetcher = fetcher,
        sourceOfTruth = sourceOfTruth
    ).build()


    fun create(): BitmapStore = store
}


interface TileFiles {
    fun stream(hikeId: String): Flow<Hike>
    fun write(hike: Hike)
    fun clear(hikeId: String)
    fun clearAll()
}


fun Bitmap.asTile(width: Int = 256, height: Int = 256): Tile {
    val stream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.PNG, 100, stream)
    val byteArray = stream.toByteArray()
    return Tile(width, height, byteArray)
}