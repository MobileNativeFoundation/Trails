package org.mobilenativefoundation.trails.shared.paging.core

sealed class PagingData<Id : Any, Value : Any> {
    data class Page<Id : Any, Value : Any>(
        val params: PagingParams<Id>,
        val items: List<Value>,
        val next: PagingParams<Id>?
    ) : PagingData<Id, Value>()

    data class Item<Id : Any, Value : Any>(
        val id: Id,
        val data: Value
    ) : PagingData<Id, Value>()
}