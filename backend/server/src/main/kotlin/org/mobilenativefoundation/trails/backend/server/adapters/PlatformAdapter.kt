package org.mobilenativefoundation.trails.backend.server.adapters

import app.cash.sqldelight.ColumnAdapter
import org.mobilenativefoundation.trails.backend.models.Platform

object PlatformAdapter : ColumnAdapter<Platform, String> {
    override fun decode(databaseValue: String): Platform {
        return Platform.valueOf(databaseValue)
    }

    override fun encode(value: Platform): String {
        return value.name
    }
}