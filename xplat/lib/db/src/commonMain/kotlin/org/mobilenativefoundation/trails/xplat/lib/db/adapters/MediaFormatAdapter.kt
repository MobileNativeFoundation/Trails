package org.mobilenativefoundation.trails.xplat.lib.db.adapters

import app.cash.sqldelight.ColumnAdapter
import org.mobilenativefoundation.trails.xplat.lib.models.post.MediaFormat

object MediaFormatAdapter : ColumnAdapter<MediaFormat, String> {
    override fun decode(databaseValue: String): MediaFormat =
        MediaFormat.valueOf(databaseValue)

    override fun encode(value: MediaFormat): String = value.name
}