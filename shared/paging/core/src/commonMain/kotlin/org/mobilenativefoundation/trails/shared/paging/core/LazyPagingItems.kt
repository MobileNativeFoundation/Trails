package org.mobilenativefoundation.trails.shared.paging.core

class LazyPagingItems<Key : Any, Value : Any>(
    private val items: List<PagingData.Item<Key, Value>>,
    private val onLoadMore: () -> Unit
) {
    val size: Int
        get() = items.size

    operator fun get(index: Int): Value {
        if (index == size - 1) {
            onLoadMore()
        }
        return items[index].data
    }

}