package org.mobilenativefoundation.trails.xplat.lib.rest.api.operations

import kotlinx.serialization.Serializable

@Serializable
data class PostsQuery(
    val search: String? = null,
    val limit: Int = 20,
    val offset: Int = 0
)
