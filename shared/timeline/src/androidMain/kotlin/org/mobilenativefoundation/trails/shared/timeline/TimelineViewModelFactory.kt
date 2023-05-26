package org.mobilenativefoundation.trails.shared.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope

@Suppress("UNCHECKED_CAST")
class TimelineViewModelFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: TimelineRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimelineViewModel::class.java)) {
            return TimelineViewModel(coroutineScope, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class.")
    }
}