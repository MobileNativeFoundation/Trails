package org.mobilenativefoundation.trails.xplat.lib.db

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(TrailsDatabase.Schema.synchronous(), "trails.db")
    }
}