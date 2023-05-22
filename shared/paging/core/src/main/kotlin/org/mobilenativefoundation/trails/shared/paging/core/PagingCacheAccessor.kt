package org.mobilenativefoundation.trails.shared.paging.core

import org.mobilenativefoundation.store.cache5.CacheBuilder

class PagingCacheAccessor<Id : Any, Value : Any> {
    private val pages = CacheBuilder<PagingKey.Page<Id>, PagingData.Page<Id, Value>>().build()
    private val items = CacheBuilder<PagingKey.Item<Id>, PagingData.Item<Id, Value>>().build()

    fun getPage(key: PagingKey.Page<Id>): PagingData.Page<Id, Value>? = pages.getIfPresent(key)
    fun getItem(key: PagingKey.Item<Id>): PagingData.Item<Id, Value>? = items.getIfPresent(key)

    fun putPage(key: PagingKey.Page<Id>, page: PagingData.Page<Id, Value>) {
        pages.put(key, page)
    }

    fun putItem(key: PagingKey.Item<Id>, item: PagingData.Item<Id, Value>) {
        items.put(key, item)
    }

    fun invalidateAll(){
        pages.invalidateAll()
        items.invalidateAll()
    }

    fun invalidateItem(key: PagingKey.Item<Id>) {
        items.invalidate(key)
    }

    fun invalidatePage(key: PagingKey.Page<Id>){
        pages.invalidate(key)
    }
}


