package org.mobilenativefoundation.trails.shared.timeline

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.mobilenativefoundation.trails.shared.data.entity.Post
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.paging.core.PagingData
import org.mobilenativefoundation.trails.shared.paging.core.PagingKey
import org.mobilenativefoundation.trails.shared.paging.core.PagingParams
import org.mobilenativefoundation.trails.shared.paging.core.PagingRepository
import org.mobilenativefoundation.trails.shared.paging.core.PagingRepositoryRequest

class TimelineViewModel(
    private val coroutineScope: CoroutineScope,
    private val repository: PagingRepository<String, PostOverview, Post>
) {
    private val initialPagingParams = PagingParams<String>(limit = 10, after = null)

    private val state: MutableStateFlow<MutableList<PagingData.Page<String, PostOverview>>> = MutableStateFlow(mutableListOf())
    val pager: Pager<String, PostOverview> = state

    private val requests = MutableSharedFlow<PagingRepositoryRequest.Page<String>>(replay = 10)

    init {
        coroutineScope.launch { flow() }
    }

    private suspend fun flow() {
        repository.flow(requests).collect { response ->
            val nextState = state.value
            nextState.add(response.data)
            state.emit(nextState)
        }
    }

    fun onLoadMore() = coroutineScope.launch {
        val next = pager.value.last().next ?: initialPagingParams
        val key = PagingKey.Page(next)
        val request = PagingRepositoryRequest.Page(key)
        requests.emit(request)
    }
}


@Composable
fun <Key : Any, Value : Any> Pager<Key, Value>.collectAsLazyPagingItems(onLoadMore: () -> Unit) =
    this.collectAsState().value.let { items -> LazyPagingItems(items, onLoadMore) }


typealias Pager<Key, Value> = StateFlow<List<PagingData.Page<Key, Value>>>

class LazyPagingItems<Key : Any, Value : Any>(
    pages: List<PagingData.Page<Key, Value>>,
    private val onLoadMore: () -> Unit
) {
    private val items: MutableList<Value> = mutableListOf()

    init {
        pages.forEach { page ->
            items.addAll(page.items)
        }
    }

    val size: Int
        get() = items.size

    operator fun get(index: Int): Value {
        if (index == size - 1) {
            onLoadMore()
        }
        return items[index]
    }

}

@Composable
fun Timeline(viewModel: TimelineViewModel) {
    val lazyPagingItems = viewModel.pager.collectAsLazyPagingItems(viewModel::onLoadMore)
    LazyColumn {
        pagingItems(lazyPagingItems) {
            Text(text = it.id)
        }
    }
}

fun <Key : Any, Value : Any> LazyListScope.pagingItems(
    lazyPagingItems: LazyPagingItems<Key, Value>,
    content: @Composable LazyItemScope.(item: Value) -> Unit
) {
    items(lazyPagingItems.size) { index ->
        val item = lazyPagingItems[index]
        content(item)
    }
}