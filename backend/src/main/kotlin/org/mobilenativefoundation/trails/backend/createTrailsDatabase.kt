package org.mobilenativefoundation.trails.backend

import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import org.mobilenativefoundation.trails.backend.adapters.CreatorAdapter
import org.mobilenativefoundation.trails.backend.adapters.MentionAdapter
import org.mobilenativefoundation.trails.backend.adapters.PostAdapter

fun createTrailsDatabase(): TrailsDatabase {
    val dataSource = createHikariDataSource()
    val driver = dataSource.asJdbcDriver()
    return TrailsDatabase(driver, CreatorAdapter, MentionAdapter, PostAdapter)
}