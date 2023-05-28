package org.mobilenativefoundation.trails.shared.paging.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.StateFlow
import org.mobilenativefoundation.trails.shared.paging.core.Identifiable
import org.mobilenativefoundation.trails.shared.paging.core.PagingState

@Composable
fun <Id : Any, AsSingle : Identifiable<Id>> StateFlow<PagingState<Id, *, AsSingle>>.collectAsLazyPagingItems(
    onLoadMore: () -> Unit
) = collectAsState().value.let { state ->
    val items = when (state) {
        is PagingState.Data -> state.items
        PagingState.Initial -> listOf()
    }
    LazyPagingItems(items, onLoadMore)
}