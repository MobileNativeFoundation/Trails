package org.mobilenativefoundation.trails.shared.paging.core

sealed class PagingKey<out Id : Any> {
    data class Page<Id : Any>(val params: PagingParams<Id>) : PagingKey<Id>()
    data class Item<Id : Any>(val id: Id) : PagingKey<Id>()

}