package org.mobilenativefoundation.trails.shared.paging.core

import org.mobilenativefoundation.store.cache5.Cache

class PagingCache<Id : Any, Value : Any>(
    private val accessor: PagingCacheAccessor<Id, Value>
) : Cache<PagingKey<Id>, PagingData<Id, Value>> {
    override fun getAllPresent(keys: List<*>): Map<PagingKey<Id>, PagingData<Id, Value>> {

        val map = mutableMapOf<PagingKey<Id>, PagingData<Id, Value>>()

        keys.filterIsInstance<PagingKey<Id>>().forEach { key ->
            when (key) {
                is PagingKey.Item -> {
                    val item = accessor.getItem(key)
                    item?.let { map[key] = it }
                }

                is PagingKey.Page -> {
                    val page = accessor.getPage(key)
                    page?.let { map[key] = it }
                }
            }
        }

        return map
    }

    override fun invalidateAll() {
        accessor.invalidateAll()
    }

    override fun size(): Long {
        // TODO(mramotar)
        return -1
    }

    override fun putAll(map: Map<PagingKey<Id>, PagingData<Id, Value>>) {
        map.entries.forEach { (key, value) ->
            when (key) {
                is PagingKey.Item -> {
                    require(value is PagingData.Item)
                    accessor.putItem(key, value)
                }

                is PagingKey.Page -> {
                    require(value is PagingData.Page)
                    accessor.putPage(key, value)
                }
            }
        }
    }

    override fun put(key: PagingKey<Id>, value: PagingData<Id, Value>) {
        when (key) {
            is PagingKey.Item -> {
                require(value is PagingData.Item)
                accessor.putItem(key, value)
            }

            is PagingKey.Page -> {
                require(value is PagingData.Page)
                accessor.putPage(key, value)
            }
        }
    }

    override fun invalidateAll(keys: List<PagingKey<Id>>) {
        keys.forEach { key ->
            when (key) {
                is PagingKey.Item -> accessor.invalidateItem(key)
                is PagingKey.Page -> accessor.invalidatePage(key)
            }
        }
    }

    override fun invalidate(key: PagingKey<Id>) {
        when (key) {
            is PagingKey.Item -> accessor.invalidateItem(key)
            is PagingKey.Page -> accessor.invalidatePage(key)
        }
    }

    override fun getOrPut(key: PagingKey<Id>, valueProducer: () -> PagingData<Id, Value>): PagingData<Id, Value> {
        return when (key) {
            is PagingKey.Item -> {
                val item = accessor.getItem(key)
                if (item != null) {
                    item
                } else {
                    val produced = valueProducer()
                    require(produced is PagingData.Item)
                    accessor.putItem(key, produced)
                    produced
                }
            }

            is PagingKey.Page -> {
                val page = accessor.getPage(key)
                if (page != null) {
                    page
                } else {
                    val produced = valueProducer()
                    require(produced is PagingData.Page)
                    accessor.putPage(key, produced)
                    produced
                }
            }
        }
    }

    override fun getIfPresent(key: PagingKey<Id>): PagingData<Id, Value>? {
        return when (key) {
            is PagingKey.Item -> accessor.getItem(key)
            is PagingKey.Page -> accessor.getPage(key)
        }
    }

}