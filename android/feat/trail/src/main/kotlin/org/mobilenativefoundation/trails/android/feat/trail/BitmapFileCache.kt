package org.mobilenativefoundation.trails.android.feat.trail

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject


interface BitmapFileCache {
    fun find(key: BitmapKey): Bitmap?
    fun put(key: BitmapKey, bitmap: Bitmap)
    fun clear(key: BitmapKey)
}

class RealBitmapFileCache @Inject constructor(application: Application) : BitmapFileCache {
    private val context = application.applicationContext

    override fun find(key: BitmapKey): Bitmap? {
        val bitmapFile = File(key.dir(), key.path())
        return if (bitmapFile.exists()) {
            BitmapFactory.decodeFile(bitmapFile.absolutePath)
        } else {
            null
        }
    }

    private fun BitmapKey.dir() = "bitmaps/$zoom/$latitude"
    private fun BitmapKey.fileName() = "$longitude.png"

    private fun BitmapKey.path() = dir() + "/" + fileName()

    override fun put(key: BitmapKey, bitmap: Bitmap) {
        try {
            val bitmapDir = File(context.filesDir, key.dir())
            if (!bitmapDir.exists()) {
                bitmapDir.mkdirs()
            }

            val bitmapFile = File(bitmapDir, key.fileName())
            FileOutputStream(bitmapFile).use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    override fun clear(key: BitmapKey) {
        TODO("Not yet implemented")
    }
}