package org.mobilenativefoundation.trails.backend

import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.mobilenativefoundation.trails.backend.plugins.configureRouting
import org.mobilenativefoundation.trails.backend.plugins.configureSerialization


fun main(args: Array<String>): Unit =
    EngineMain.main(args)

fun Application.module() {
    val dataSource = createHikariDataSource()
    val driver = dataSource.asJdbcDriver()
    val trailsDatabase = TrailsDatabase(driver)

    configureSerialization()
    configureRouting(trailsDatabase)
}