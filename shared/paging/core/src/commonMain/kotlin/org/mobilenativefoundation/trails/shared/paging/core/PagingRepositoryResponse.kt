package org.mobilenativefoundation.trails.shared.paging.core

sealed class PagingRepositoryResponse<Id : Any, Value : Any, Detail : Any> {
    data class Page<Id : Any, Value : Any>(val data: PagingData.Page<Id, Value>) : PagingRepositoryResponse<Id, Value, Nothing>()
    data class PagedItem<Id : Any, Value : Any>(val data: PagingData.Item<Id, Value>) : PagingRepositoryResponse<Id, Value, Nothing>()
    data class DetailedItem<Detail : Any>(val data: Detail) : PagingRepositoryResponse<Nothing, Detail, Nothing>()
}