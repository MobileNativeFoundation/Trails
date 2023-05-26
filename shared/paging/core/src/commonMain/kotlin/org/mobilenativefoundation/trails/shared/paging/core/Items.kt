package org.mobilenativefoundation.trails.shared.paging.core

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable

fun <Key : Any, Value : Any> LazyListScope.items(
    lazyPagingItems: LazyPagingItems<Key, Value>,
    content: @Composable LazyItemScope.(item: Value) -> Unit
) {

    items(lazyPagingItems.size) { index ->
        val item = lazyPagingItems[index]
        content(item)
    }
}