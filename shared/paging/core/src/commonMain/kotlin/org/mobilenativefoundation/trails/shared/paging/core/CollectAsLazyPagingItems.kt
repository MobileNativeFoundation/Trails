package org.mobilenativefoundation.trails.shared.paging.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun <Key : Any, Value : Any> Pager<Key, Value>.collectAsLazyPagingItems(onLoadMore: () -> Unit) =
    this.collectAsState().value.let { feed -> LazyPagingItems(feed.items, onLoadMore) }
