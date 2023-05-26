package org.mobilenativefoundation.trails.shared.timeline.composable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.mobilenativefoundation.trails.shared.paging.core.LazyPagingItems
import org.mobilenativefoundation.trails.shared.paging.core.collectAsLazyPagingItems
import org.mobilenativefoundation.trails.shared.paging.core.items
import org.mobilenativefoundation.trails.shared.timeline.TimelineViewModel

@Composable
fun TimelineView(viewModel: TimelineViewModel) {
    val lazyPagingItems = viewModel.pager.collectAsLazyPagingItems(viewModel::onLoadMore)

    // TODO(matt-ramotar) Remember scroll position

    LazyPaging(lazyPagingItems) {
        Text(it.id)
    }
}

@Composable
fun <Key : Any, Value : Any> LazyPaging(
    items: LazyPagingItems<Key, Value>,
    content: @Composable LazyItemScope.(item: Value) -> Unit
) {
    LazyColumn {
        items(items, content)
    }
}

fun <Key : Any, Value : Any> LazyListScope.items(
    lazyPagingItems: LazyPagingItems<Key, Value>,
    content: @Composable LazyItemScope.(item: Value) -> Unit
) {

    items(lazyPagingItems.size) { index ->
        val item = lazyPagingItems[index]
        content(item)
    }
}
