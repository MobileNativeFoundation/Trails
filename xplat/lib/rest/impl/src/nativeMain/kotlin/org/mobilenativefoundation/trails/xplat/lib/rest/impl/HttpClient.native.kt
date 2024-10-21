package org.mobilenativefoundation.trails.xplat.lib.rest.impl

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Darwin) {
    config(this)

    install(ContentNegotiation) {
        json()
    }

    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
}