package org.mobilenativefoundation.trails.xplat.lib.db

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}