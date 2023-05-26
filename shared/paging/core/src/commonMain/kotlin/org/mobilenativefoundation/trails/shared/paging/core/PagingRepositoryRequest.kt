package org.mobilenativefoundation.trails.shared.paging.core

sealed class PagingRepositoryRequest<Id : Any> {
    data class Page<Id : Any>(val key: PagingKey.Page<Id>) : PagingRepositoryRequest<Id>()
    data class PagedItem<Id : Any>(val key: PagingKey.Item<Id>) : PagingRepositoryRequest<Id>()
    data class DetailedItem<Id : Any>(val key: Id) : PagingRepositoryRequest<Id>()
}