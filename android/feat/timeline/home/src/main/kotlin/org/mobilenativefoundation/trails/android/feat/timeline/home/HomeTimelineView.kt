package org.mobilenativefoundation.trails.android.feat.timeline.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import org.mobilenativefoundation.trails.shared.paging.compose.collectAsLazyPagingItems
import org.mobilenativefoundation.trails.shared.timeline.TimelineViewModel

@Composable
fun HomeTimelineView(
    timelineViewModel: TimelineViewModel = viewModel()
) {
    val lazyPagingItems =
        timelineViewModel.state.collectAsLazyPagingItems(timelineViewModel::onLoadMore)

}