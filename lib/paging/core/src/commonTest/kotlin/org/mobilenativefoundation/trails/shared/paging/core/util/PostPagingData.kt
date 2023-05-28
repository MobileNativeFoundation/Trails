package org.mobilenativefoundation.trails.shared.paging.core.util

import org.mobilenativefoundation.trails.shared.paging.core.PagingData
import org.mobilenativefoundation.trails.shared.paging.core.PagingParams

sealed class PostPagingData {
    data class Page(
        override val params: PagingParams<Int>,
        override val data: List<Post>,
        override val next: PagingParams<Int>?
    ) : PagingData.Page<Int, Post, Post>, PostPagingData()

    data class Item(
        override val id: Int,
        override val data: Post
    ) : PagingData.Item<Int, Post, Post>, PostPagingData()
}
