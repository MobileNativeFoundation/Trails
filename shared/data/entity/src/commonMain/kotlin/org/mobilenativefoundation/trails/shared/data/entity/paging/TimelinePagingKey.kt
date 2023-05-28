package org.mobilenativefoundation.trails.shared.data.entity.paging

import org.mobilenativefoundation.trails.shared.paging.core.PagingKey
import org.mobilenativefoundation.trails.shared.paging.core.PagingParams

sealed class TimelinePagingKey {
    data class Page(
        override val params: PagingParams<Int>
    ) : PagingKey.Page<Int>, TimelinePagingKey()

    data class Item(
        override val id: Int
    ) : PagingKey.Item<Int>, TimelinePagingKey()
}