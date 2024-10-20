package org.mobilenativefoundation.trails.backend.server

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.net.URI

internal fun createHikariDataSource(): HikariDataSource {
    val connection = System.getenv("CONNECTION")
    val username = System.getenv("USERNAME")
    val password = System.getenv("PASSWORD")
    val proxyUrl = URI(System.getenv("QUOTAGUARDSHIELD_URL"))

    val hikariConfig = HikariConfig().apply {
        this.jdbcUrl = connection
        this.username = username
        this.password = password
        driverClassName = "org.postgresql.Driver"
        maximumPoolSize = 10
        connectionInitSql = "SET SESSION socks_proxy_host='${proxyUrl.host}', socks_proxy_port='${proxyUrl.port}'"
    }

    return HikariDataSource(hikariConfig)
}


