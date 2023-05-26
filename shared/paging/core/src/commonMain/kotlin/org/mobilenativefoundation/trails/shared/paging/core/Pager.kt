package org.mobilenativefoundation.trails.shared.paging.core

import kotlinx.coroutines.flow.StateFlow

typealias Pager<Key, Value> = StateFlow<Feed<Key, Value>>

data class Feed<Key : Any, Value : Any>(
    val items: List<PagingData.Item<Key, Value>>,
    val next: PagingParams<Key>? = null
)