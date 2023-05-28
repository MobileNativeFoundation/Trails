package org.mobilenativefoundation.trails.shared.paging.compose

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable


fun <Id : Any, AsSingle : Any> LazyListScope.items(
    lazyPagingItems: LazyPagingItems<Id, AsSingle>,
    content: @Composable LazyItemScope.(item: AsSingle) -> Unit
) {
    items(lazyPagingItems.size) { index ->
        val item = lazyPagingItems[index]
        content(item)
    }
}
