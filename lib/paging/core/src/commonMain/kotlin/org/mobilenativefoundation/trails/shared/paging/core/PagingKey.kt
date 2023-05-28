package org.mobilenativefoundation.trails.shared.paging.core

sealed interface PagingKey<out Id : Any> {
    interface Page<Id : Any> : PagingKey<Id> {
        val params: PagingParams<Id>
    }

    interface Item<Id : Any> : PagingKey<Id> {
        val id: Id
    }
}
