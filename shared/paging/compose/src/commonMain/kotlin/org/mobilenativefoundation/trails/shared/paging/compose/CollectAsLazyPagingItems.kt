package org.mobilenativefoundation.trails.shared.paging.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.mobilenativefoundation.trails.shared.paging.core.Pager

@Composable
fun <Id : Any, AsSingle : Any> Pager<Id, *, AsSingle>.collectAsLazyPagingItems(onLoadMore: () -> Unit) =
    this.state.collectAsState().value.let { state -> LazyPagingItems(state.items, onLoadMore) }