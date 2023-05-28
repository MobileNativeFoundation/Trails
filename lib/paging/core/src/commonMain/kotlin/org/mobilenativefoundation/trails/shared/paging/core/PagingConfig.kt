package org.mobilenativefoundation.trails.shared.paging.core

interface PagingConfig<Id : Any> {
    val initialPagingKey: PagingKey<Id>

    companion object {
        fun <Id : Any> of(initialPagingKey: PagingKey<Id>): PagingConfig<Id> = object : PagingConfig<Id> {
            override val initialPagingKey: PagingKey<Id> = initialPagingKey
        }
    }
}
