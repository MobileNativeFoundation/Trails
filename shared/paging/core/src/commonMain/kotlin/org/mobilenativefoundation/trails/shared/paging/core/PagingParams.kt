package org.mobilenativefoundation.trails.shared.paging.core

import kotlinx.serialization.Serializable

@Serializable
data class PagingParams<Id : Any>(
    val limit: Int,
    val after: Id?
)