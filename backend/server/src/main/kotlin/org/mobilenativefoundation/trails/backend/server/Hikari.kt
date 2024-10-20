package org.mobilenativefoundation.trails.backend.server

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.yaml.*

internal fun createHikariDataSource(): HikariDataSource {
    val databaseConfig = YamlConfig("backend/server/postgres.yaml")

    val jdbcURL = databaseConfig?.property("services.postgres.environment.JDBC_URL")?.getString()
    val username = databaseConfig?.property("services.postgres.environment.USERNAME")?.getString()
    val password = databaseConfig?.property("services.postgres.environment.PASSWORD")?.getString()

    val hikariConfig = HikariConfig().apply {
        this.jdbcUrl = jdbcURL
        this.username = username
        this.password = password
        driverClassName = "org.postgresql.Driver"
        maximumPoolSize = 10
    }

    return HikariDataSource(hikariConfig)
}


