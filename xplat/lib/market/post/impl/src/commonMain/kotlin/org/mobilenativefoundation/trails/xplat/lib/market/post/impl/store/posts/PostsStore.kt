package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.store.posts

import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.PostsQuery

typealias PostsStore = Store<PostsQuery, List<Int>>
