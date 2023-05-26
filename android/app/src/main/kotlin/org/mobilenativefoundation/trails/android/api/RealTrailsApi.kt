package org.mobilenativefoundation.trails.android.api

import kotlinx.coroutines.flow.Flow
import org.mobilenativefoundation.trails.shared.data.api.TrailsApi
import org.mobilenativefoundation.trails.shared.data.entity.Feed
import org.mobilenativefoundation.trails.shared.data.entity.Hike
import org.mobilenativefoundation.trails.shared.data.entity.Notification
import org.mobilenativefoundation.trails.shared.data.entity.Post
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.data.entity.Trail
import org.mobilenativefoundation.trails.shared.data.entity.User
import org.mobilenativefoundation.trails.shared.paging.core.PagingData
import org.mobilenativefoundation.trails.shared.paging.core.PagingParams

class RealTrailsApi : TrailsApi {
    override suspend fun getFeed(userId: String): Feed {
        TODO("Not yet implemented")
    }

    override suspend fun getSavedTrails(userId: String): List<Trail> {
        TODO("Not yet implemented")
    }

    override suspend fun getUnseenNotifications(userId: String): List<Notification> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(userId: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun getHike(hikeId: String): Hike {
        TODO("Not yet implemented")
    }

    override suspend fun updateHike(hike: Hike): Hike {
        TODO("Not yet implemented")
    }

    override fun syncHike(updates: Flow<Hike>): Flow<Hike> {
        TODO("Not yet implemented")
    }

    override suspend fun startHike(userId: String, trailId: String): Hike {
        TODO("Not yet implemented")
    }

    override suspend fun getHomeTimeline(
        userId: String,
        limit: Int,
        after: String?
    ): PagingData.Page<String, PostOverview> {
        val start = after?.toIntOrNull()?.plus(1) ?: 1
        val end = start + 49
        val next = PagingParams(limit = 50, after = end.toString())
        return PagingData.Page(
            params = PagingParams(limit = 50, after = after),
            items = (start..end).map { index ->
                PostOverview(
                    id = "id_$index",
                    userName = "userName_$index",
                    userAvatarUrl = "userAvatarUrl_$index",
                    hikeId = "hikeId_$index",
                    title = "title_$index",
                    body = "body_$index",
                    coverImageUrl = "coverImageUrl_$index",
                    likeIds = listOf(),
                    commentIds = listOf()
                )
            },
            next = next
        )
    }

    override suspend fun getPost(postId: String): Post {
        TODO("Not yet implemented")
    }

    override suspend fun getPostOverview(postId: String): PostOverview {
        TODO("Not yet implemented")
    }

}