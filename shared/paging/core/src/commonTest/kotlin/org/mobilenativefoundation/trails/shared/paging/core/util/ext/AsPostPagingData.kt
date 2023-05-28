package org.mobilenativefoundation.trails.shared.paging.core.util.ext

import org.mobilenativefoundation.trails.shared.paging.core.util.Post
import org.mobilenativefoundation.trails.shared.paging.core.util.PostPagingData
import org.mobilenativefoundation.trails.shared.paging.core.util.PostPagingParams
import org.mobilenativefoundation.trails.shared.paging.core.PagingData
import org.mobilenativefoundation.trails.shared.paging.core.PagingParams

fun PagingData<Int, Post, Post>.asPostPagingData(): PostPagingData = when (this) {
    is PagingData.Item -> asPostPagingData()
    is PagingData.Page -> asPostPagingData()
}

fun PagingData.Item<Int, Post, Post>.asPostPagingData(): PostPagingData.Item =
    PostPagingData.Item(id, data)

fun PagingData.Page<Int, Post, Post>.asPostPagingData(): PostPagingData.Page =
    PostPagingData.Page(params, data, next)

fun PagingParams<Int>.asPostPagingParams() = PostPagingParams(loadSize, after, type)
