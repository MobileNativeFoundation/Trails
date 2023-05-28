package org.mobilenativefoundation.trails.shared.timeline

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.data.entity.paging.TimelinePagingKey
import org.mobilenativefoundation.trails.shared.data.entity.paging.TimelinePagingParams
import org.mobilenativefoundation.trails.shared.paging.core.Pager
import org.mobilenativefoundation.trails.shared.paging.core.PagingParams
import org.mobilenativefoundation.trails.shared.paging.core.PagingState

internal class TimelineViewModelDelegate(
    private val coroutineScope: CoroutineScope,
    private val pager: Pager<Int, PostOverview, PostOverview>
) {
    fun onLoadMore() {
        coroutineScope.launch {
            pager.load(generateNextKey())
        }
    }

    private fun generateNextKey(): TimelinePagingKey.Page {
        val pagingState = pager.state.value
        val params = if (pagingState is PagingState.Data) {
            pagingState.next ?: initialPagingParams
        } else {
            initialPagingParams
        }
        return TimelinePagingKey.Page(params)
    }

    private val initialPagingParams = TimelinePagingParams(
        loadSize = LOAD_SIZE,
        type = loadType,
        offset = null
    )

    companion object {
        private const val LOAD_SIZE = 20
        private val loadType = PagingParams.Type.Append
    }
}
