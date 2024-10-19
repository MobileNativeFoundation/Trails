package org.mobilenativefoundation.trails.xplat.lib.rest.api.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateResortArgs(
    val slug: String,
    val name: String,
    val country: Country,
    val region: String? = null,
    val href: String? = null,
    val units: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
)