package org.mobilenativefoundation.trails.shared.timeline.composable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.paging.compose.collectAsLazyPagingItems
import org.mobilenativefoundation.trails.shared.paging.compose.items
import org.mobilenativefoundation.trails.shared.timeline.TimelineViewModel

@Composable
fun TimelineView(viewModel: TimelineViewModel, content: @Composable (PostOverview) -> Unit) {
    val pagingItems = viewModel.state.collectAsLazyPagingItems(viewModel::onLoadMore)

    // TODO(matt-ramotar) Remember scroll position

    LazyColumn {
        items(pagingItems) {
            content(it)
        }
    }
}

