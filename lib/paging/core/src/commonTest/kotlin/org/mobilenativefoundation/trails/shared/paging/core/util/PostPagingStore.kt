package org.mobilenativefoundation.trails.shared.paging.core.util

import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.trails.shared.paging.core.PagingData
import org.mobilenativefoundation.trails.shared.paging.core.PagingKey

typealias PostPagingStore = Store<PagingKey<Int>, PagingData<Int, Post, Post>>
