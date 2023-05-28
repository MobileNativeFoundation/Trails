package org.mobilenativefoundation.trails.shared.paging.compose

import org.mobilenativefoundation.trails.shared.paging.core.Identifiable
import org.mobilenativefoundation.trails.shared.paging.core.PagingData


class LazyPagingItems<Id : Any, AsSingle : Identifiable<Id>>(
    private val items: List<PagingData.Item<Id, *, AsSingle>>,
    private val onLoadMore: () -> Unit
) {
    val size: Int
        get() = items.size

    operator fun get(index: Int): AsSingle {
        if (index == size - 1) {
            onLoadMore()
        }
        return items[index].data
    }
}