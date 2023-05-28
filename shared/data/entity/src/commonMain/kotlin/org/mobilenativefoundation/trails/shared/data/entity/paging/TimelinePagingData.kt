package org.mobilenativefoundation.trails.shared.data.entity.paging

import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.paging.core.PagingData

sealed class TimelinePagingData {
    data class Page(
        override val params: TimelinePagingParams,
        override val data: List<PostOverview>,
        override val next: TimelinePagingParams?
    ) : PagingData.Page<Int, PostOverview, PostOverview>, TimelinePagingData()

    data class Item(
        override val id: Int,
        override val data: PostOverview
    ) : PagingData.Item<Int, PostOverview, PostOverview>, TimelinePagingData()
}