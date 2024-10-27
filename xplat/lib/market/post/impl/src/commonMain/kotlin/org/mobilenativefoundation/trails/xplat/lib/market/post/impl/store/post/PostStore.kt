package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.store.post

import org.mobilenativefoundation.store.core5.ExperimentalStoreApi
import org.mobilenativefoundation.store.store5.MutableStore
import org.mobilenativefoundation.trails.xplat.lib.models.post.CompositePost


@OptIn(ExperimentalStoreApi::class)
typealias PostStore = MutableStore<Int, CompositePost>