package org.mobilenativefoundation.trails.shared.paging.core

class PagingCacheBuilder<Id : Any, Value : Any> {
    fun build(): PagingCache<Id, Value> {
        val delegate = PagingCacheAccessor<Id, Value>()
        return PagingCache<Id, Value>(delegate)
    }
}