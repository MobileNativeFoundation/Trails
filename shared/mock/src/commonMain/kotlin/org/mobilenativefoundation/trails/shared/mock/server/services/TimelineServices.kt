package org.mobilenativefoundation.trails.shared.mock.server.services

import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.data.entity.paging.TimelinePagingData
import org.mobilenativefoundation.trails.shared.data.entity.paging.TimelinePagingParams

class TimelineServices {
    fun get(params: TimelinePagingParams): TimelinePagingData.Page {
        val start = params.offset?.plus(1) ?: 1
        val end = start + params.loadSize
        val next =
            TimelinePagingParams(loadSize = params.loadSize, offset = end, type = params.type)
        return TimelinePagingData.Page(
            params = params,
            data = (start..end).map { id ->
                PostOverview(
                    id = id,
                    userId = Math.random().toInt(),
                    userName = "Tag",
                    userAvatarUrl = "",
                    hikeId = Math.random().toInt(),
                    title = "$id",
                    body = "Awesome day!",
                    coverImageUrl = "",
                    likeIds = listOf(),
                    commentIds = listOf()
                )

            },
            next = next
        )
    }
}