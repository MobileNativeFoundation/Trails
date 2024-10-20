package org.mobilenativefoundation.trails.backend.server

import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import org.mobilenativefoundation.trails.backend.server.adapters.CreatorAdapter
import org.mobilenativefoundation.trails.backend.server.adapters.MentionAdapter
import org.mobilenativefoundation.trails.backend.server.adapters.PostAdapter

fun createTrailsDatabase(): TrailsDatabase {
    val dataSource = createHikariDataSource()
    val driver = dataSource.asJdbcDriver()
    return TrailsDatabase(driver, CreatorAdapter, MentionAdapter, PostAdapter)
}