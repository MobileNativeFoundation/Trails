package org.mobilenativefoundation.trails.shared.mock.server

import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlag
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatuses
import org.mobilenativefoundation.trails.shared.data.entity.paging.TimelinePagingData
import org.mobilenativefoundation.trails.shared.data.entity.paging.TimelinePagingParams

class MockServer {

    private val featureFlags = MockFeatureFlags()

    fun featureFlagStatuses(userId: Int, platform: Platform) =
        FeatureFlagStatuses(
            items = featureFlags.statuses.values.toList()
        )

    fun featureFlag(key: String): FeatureFlag? =
        featureFlags.flags[key]

    fun timeline(params: TimelinePagingParams): TimelinePagingData.Page {
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