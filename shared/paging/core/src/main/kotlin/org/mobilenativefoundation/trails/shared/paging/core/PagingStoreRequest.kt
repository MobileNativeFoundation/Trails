package org.mobilenativefoundation.trails.shared.paging.core

sealed class PagingStoreRequest<Id : Any> {
    data class Page<Id : Any>(val key: PagingKey.Page<Id>) : PagingStoreRequest<Id>()
    data class PagedItem<Id : Any>(val key: PagingKey.Item<Id>) : PagingStoreRequest<Id>()
    data class DetailedItem<Id : Any>(val key: Id) : PagingStoreRequest<Id>()
}