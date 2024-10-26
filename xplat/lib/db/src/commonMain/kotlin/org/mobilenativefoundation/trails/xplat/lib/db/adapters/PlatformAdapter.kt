package org.mobilenativefoundation.trails.xplat.lib.db.adapters

import app.cash.sqldelight.ColumnAdapter
import org.mobilenativefoundation.trails.xplat.lib.models.post.Platform

object PlatformAdapter : ColumnAdapter<Platform, String> {
    override fun decode(databaseValue: String): Platform =
        Platform.valueOf(databaseValue)

    override fun encode(value: Platform): String = value.name
}