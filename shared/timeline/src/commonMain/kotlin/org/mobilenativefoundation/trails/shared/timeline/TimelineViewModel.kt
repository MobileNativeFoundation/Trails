package org.mobilenativefoundation.trails.shared.timeline

import kotlinx.coroutines.flow.StateFlow
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.paging.core.PagingState


expect class TimelineViewModel {
    fun onLoadMore()
    val state: StateFlow<PagingState<Int, PostOverview, PostOverview>>
}

