package org.mobilenativefoundation.trails.common.networking.impl.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

internal suspend inline fun <reified A : Any, reified R : Any> HttpClient.post(args: A, url: String): R {
    val response = this.post(url) {
        contentType(ContentType.Application.Json)
        setBody(args)
    }
    return response.body()
}