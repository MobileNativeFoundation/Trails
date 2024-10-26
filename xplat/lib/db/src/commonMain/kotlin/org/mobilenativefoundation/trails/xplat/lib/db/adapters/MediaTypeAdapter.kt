package org.mobilenativefoundation.trails.xplat.lib.db.adapters

import app.cash.sqldelight.ColumnAdapter
import org.mobilenativefoundation.trails.xplat.lib.models.post.MediaType

object MediaTypeAdapter : ColumnAdapter<MediaType, String> {
    override fun decode(databaseValue: String): MediaType =
        MediaType.valueOf(databaseValue)

    override fun encode(value: MediaType): String = value.name
}