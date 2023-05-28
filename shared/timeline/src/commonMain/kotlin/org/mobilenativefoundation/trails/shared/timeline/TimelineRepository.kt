package org.mobilenativefoundation.trails.shared.timeline

import org.mobilenativefoundation.trails.shared.data.entity.Post
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.paging.core0.PagingRepository

typealias TimelineRepository = PagingRepository<String, PostOverview, Post>