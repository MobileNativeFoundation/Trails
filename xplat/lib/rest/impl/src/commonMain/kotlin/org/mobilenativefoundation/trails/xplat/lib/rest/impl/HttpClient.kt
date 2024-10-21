package org.mobilenativefoundation.trails.xplat.lib.rest.impl

import io.ktor.client.*

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient
