package org.mobilenativefoundation.trails.shared.paging.core.util

import org.mobilenativefoundation.trails.shared.paging.core.PagingKey

sealed class PostPagingKey {
    data class Page(
        override val params: PostPagingParams
    ) : PagingKey.Page<Int>, PostPagingKey()

    data class Item(
        override val id: Int
    ) : PagingKey.Item<Int>, PostPagingKey()
}
