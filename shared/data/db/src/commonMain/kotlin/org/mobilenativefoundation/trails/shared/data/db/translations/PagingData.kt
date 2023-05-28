package org.mobilenativefoundation.trails.shared.data.db.translations

import org.mobilenativefoundation.trails.shared.data.db.FindByParamsAndPopulate
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.data.entity.paging.TimelinePagingData
import org.mobilenativefoundation.trails.shared.data.entity.paging.TimelinePagingParams

fun List<FindByParamsAndPopulate>.output(): TimelinePagingData.Page {
    val core = getOrNull(0)
    val params = core?.paramsLoadSize?.let { loadSize ->
        core.paramsType?.let { type ->
            TimelinePagingParams(loadSize, core.paramsAfter, type)
        }
    } ?: throw Exception("Missing params")


    val data = mapNotNull { item ->
        if (
            item.postOverviewId != null &&
            item.postOverviewUserId != null &&
            item.postOverviewUserName != null &&
            item.postOverviewUserAvatarUrl != null &&
            item.postOverviewHikeId != null &&
            item.postOverviewTitle != null &&
            item.postOverviewBody != null &&
            item.postOverviewCoverImageUrl != null
        ) {

            PostOverview(
                id = item.postOverviewId,
                userId = item.postOverviewUserId,
                userName = item.postOverviewUserName,
                userAvatarUrl = item.postOverviewUserAvatarUrl,
                hikeId = item.postOverviewHikeId,
                title = item.postOverviewTitle,
                body = item.postOverviewBody,
                coverImageUrl = item.postOverviewCoverImageUrl,
                likeIds = listOf(),
                commentIds = listOf()
            )
        } else {
            null
        }
    }
    return TimelinePagingData.Page(
        params = params,
        data = data,
        next = core.next
    )
}