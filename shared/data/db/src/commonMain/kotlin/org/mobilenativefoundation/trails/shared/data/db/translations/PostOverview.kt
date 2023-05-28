package org.mobilenativefoundation.trails.shared.data.db.translations

import org.mobilenativefoundation.trails.shared.data.db.PostOverviewSq
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview

fun PostOverviewSq.output() = PostOverview(
    id = id,
    userId = userId,
    userName,
    userAvatarUrl,
    hikeId,
    title,
    body,
    coverImageUrl,
    likeIds = listOf(),
    commentIds = listOf()
)