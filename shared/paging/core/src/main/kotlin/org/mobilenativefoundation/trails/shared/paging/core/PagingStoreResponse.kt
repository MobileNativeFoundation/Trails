package org.mobilenativefoundation.trails.shared.paging.core

sealed class PagingStoreResponse<Id : Any, Value : Any, Detail : Any> {


    data class Page<Id : Any, Value : Any>(val data: PagingData.Page<Id, Value>) : PagingStoreResponse<Id, Value, Nothing>()
    data class PagedItem<Id : Any, Value : Any>(val data: PagingData.Item<Id, Value>) : PagingStoreResponse<Id, Value, Nothing>()
    data class DetailedItem<Detail : Any>(val data: Detail) : PagingStoreResponse<Nothing, Detail, Nothing>()
}