package org.mobilenativefoundation.trails.shared.paging.core.impl

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.mobilenativefoundation.store.cache5.Cache
import org.mobilenativefoundation.trails.shared.paging.core.Identifiable
import org.mobilenativefoundation.trails.shared.paging.core.KeyGenerator
import org.mobilenativefoundation.trails.shared.paging.core.PagingConverter
import org.mobilenativefoundation.trails.shared.paging.core.PagingData
import org.mobilenativefoundation.trails.shared.paging.core.PagingKey

class PagingCache<Id : Any, InCollection : Identifiable<Id>, AsSingle : Identifiable<Id>>(
    private val scope: CoroutineScope,
    private val converter: PagingConverter<Id, InCollection, AsSingle> = PagingConverter.default(),
    private val keyGenerator: KeyGenerator<Id, InCollection, AsSingle> = KeyGenerator.default()
) : Cache<PagingKey<Id>, PagingData<Id, InCollection, AsSingle>> {

    private val accessor = PagingCacheAccessor<Id, InCollection, AsSingle>()
    override fun getAllPresent(keys: List<*>): Map<PagingKey<Id>, PagingData<Id, InCollection, AsSingle>> {

        val map = mutableMapOf<PagingKey<Id>, PagingData<Id, InCollection, AsSingle>>()

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
        throw UnsupportedOperationException()
    }

    override fun putAll(map: Map<PagingKey<Id>, PagingData<Id, InCollection, AsSingle>>) {
        map.entries.forEach { (key, value) ->
            put(key, value)
        }
    }

    override fun put(key: PagingKey<Id>, value: PagingData<Id, InCollection, AsSingle>) {
        when (key) {
            is PagingKey.Item -> {
                require(value is PagingData.Item)
                accessor.putItem(key, value)
            }

            is PagingKey.Page -> {
                require(value is PagingData.Page)
                accessor.putPage(key, value)

                scope.launch {
                    value.data.map { converter.from(it) }.forEach {
                        val pagingKey = keyGenerator.fromSingle(it)
                        val pagingData = converter.asPagingData(it)
                        accessor.putItem(pagingKey, pagingData)
                    }
                }
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

    override fun getOrPut(
        key: PagingKey<Id>,
        valueProducer: () -> PagingData<Id, InCollection, AsSingle>
    ): PagingData<Id, InCollection, AsSingle> {
        return when (key) {
            is PagingKey.Item -> {
                val item = accessor.getItem(key)
                if (item != null) {
                    item
                } else {
                    val producedItem = valueProducer()
                    require(producedItem is PagingData.Item)
                    put(key, producedItem)
                    producedItem
                }
            }

            is PagingKey.Page -> {
                val page = accessor.getPage(key)
                if (page != null) {
                    page
                } else {
                    val produced = valueProducer()
                    require(produced is PagingData.Page)
                    put(key, produced)
                    produced
                }
            }
        }
    }

    override fun getIfPresent(key: PagingKey<Id>): PagingData<Id, InCollection, AsSingle>? {
        return when (key) {
            is PagingKey.Item -> accessor.getItem(key)
            is PagingKey.Page -> accessor.getPage(key)
        }
    }
}
