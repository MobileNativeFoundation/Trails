package org.mobilenativefoundation.trails.shared.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.paging.core.Pager

@Suppress("UNCHECKED_CAST")
class TimelineViewModelFactory(
    private val coroutineScope: CoroutineScope,
    private val pager: Pager<Int, PostOverview, PostOverview>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimelineViewModel::class.java)) {
            return TimelineViewModel(coroutineScope, pager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class.")
    }
}