package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.store.posts

import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.trails.xplat.lib.models.post.CompositePost

typealias PostsStore = Store<String, List<CompositePost>>
