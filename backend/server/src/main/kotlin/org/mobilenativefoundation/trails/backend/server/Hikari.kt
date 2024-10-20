package org.mobilenativefoundation.trails.backend.server

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

internal fun createHikariDataSource(): HikariDataSource {
    val connection = System.getenv("CONNECTION")
    val username = System.getenv("USERNAME")
    val password = System.getenv("PASSWORD")

    val hikariConfig = HikariConfig().apply {
        this.jdbcUrl = connection
        this.username = username
        this.password = password
        driverClassName = "org.postgresql.Driver"
        maximumPoolSize = 10
    }

    return HikariDataSource(hikariConfig)
}


