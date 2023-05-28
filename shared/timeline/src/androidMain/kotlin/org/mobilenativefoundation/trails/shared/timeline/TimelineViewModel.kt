package org.mobilenativefoundation.trails.shared.timeline

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.paging.core0.Feed
import org.mobilenativefoundation.trails.shared.paging.core0.Pager
import org.mobilenativefoundation.trails.shared.paging.core0.PagingData
import org.mobilenativefoundation.trails.shared.paging.core0.PagingKey
import org.mobilenativefoundation.trails.shared.paging.core0.PagingParams
import org.mobilenativefoundation.trails.shared.paging.core0.PagingRepositoryRequest

actual class TimelineViewModel(
    private val coroutineScope: CoroutineScope,
    private val repository: TimelineRepository
) : ViewModel() {
    actual fun onLoadMore() {
        coroutineScope.launch {
            val next = pager.value.next ?: initialPagingParams
            val key = PagingKey.Page(next)
            val request = PagingRepositoryRequest.Page(key)
            requests.emit(request)
        }
    }


    private val initialPagingParams = PagingParams<String>(limit = 50, after = null)

    private val state: MutableStateFlow<Feed<String, PostOverview>> =
        MutableStateFlow(Feed(items = listOf()))
    actual val pager: Pager<String, PostOverview> = state

    private val requests = MutableSharedFlow<PagingRepositoryRequest.Page<String>>(
        replay = 10
    )

    init {
        coroutineScope.launch {
            requests.emit(PagingRepositoryRequest.Page(PagingKey.Page(initialPagingParams)))
            flow()
        }
    }

    private suspend fun flow() {
        repository.flow(requests).collect { response ->
            val nextItems = state.value.items.toMutableList()
            response.data.items.forEach { nextItems.add(PagingData.Item(it.id, it)) }
            val nextState = Feed(items = nextItems, next = response.data.next)
            state.emit(nextState)
        }
    }
}