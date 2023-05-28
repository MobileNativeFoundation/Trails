package org.mobilenativefoundation.trails.shared.paging.core

interface PagingParams<Id : Any> {
    val loadSize: Int
    val after: Id?
    val type: Type

    enum class Type { Refresh, Append, Prepend }
}
