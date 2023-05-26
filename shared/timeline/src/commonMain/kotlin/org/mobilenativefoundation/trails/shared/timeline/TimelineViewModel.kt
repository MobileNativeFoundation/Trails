package org.mobilenativefoundation.trails.shared.timeline

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.paging.core.Pager
import org.mobilenativefoundation.trails.shared.paging.core.collectAsLazyPagingItems
import org.mobilenativefoundation.trails.shared.paging.core.items


expect class TimelineViewModel {
    fun onLoadMore()
    val pager: Pager<String, PostOverview>
}



