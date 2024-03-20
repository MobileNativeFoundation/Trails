package org.mobilenativefoundation.trails.common.networking.impl.client

import io.ktor.client.*

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient