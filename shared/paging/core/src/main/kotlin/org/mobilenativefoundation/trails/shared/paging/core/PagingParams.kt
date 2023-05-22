package org.mobilenativefoundation.trails.shared.paging.core

data class PagingParams<Id : Any>(
    val limit: Int,
    val after: Id?
)