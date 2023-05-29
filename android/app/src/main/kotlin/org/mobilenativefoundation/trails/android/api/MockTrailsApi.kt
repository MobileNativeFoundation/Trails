package org.mobilenativefoundation.trails.android.api

import android.content.res.Resources.NotFoundException
import kotlinx.coroutines.flow.Flow
import org.mobilenativefoundation.trails.shared.data.api.TrailsApi
import org.mobilenativefoundation.trails.shared.data.entity.Feed
import org.mobilenativefoundation.trails.shared.data.entity.Hike
import org.mobilenativefoundation.trails.shared.data.entity.Notification
import org.mobilenativefoundation.trails.shared.data.entity.Post
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.data.entity.Trail
import org.mobilenativefoundation.trails.shared.data.entity.User
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlag
import org.mobilenativefoundation.trails.shared.data.entity.paging.TimelinePagingData
import org.mobilenativefoundation.trails.shared.data.entity.paging.TimelinePagingParams
import org.mobilenativefoundation.trails.shared.mock.server.MockServer

class MockTrailsApi : TrailsApi {

    private val server = MockServer()

    override suspend fun getFeed(userId: Int): Feed {
        TODO("Not yet implemented")
    }

    override suspend fun getSavedTrails(userId: Int): List<Trail> {
        TODO("Not yet implemented")
    }

    override suspend fun getUnseenNotifications(userId: Int): List<Notification> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(userId: Int): User {
        TODO("Not yet implemented")
    }

    override suspend fun getHike(hikeId: Int): Hike {
        TODO("Not yet implemented")
    }

    override suspend fun updateHike(hike: Hike): Hike {
        TODO("Not yet implemented")
    }

    override fun syncHike(updates: Flow<Hike>): Flow<Hike> {
        TODO("Not yet implemented")
    }

    override suspend fun startHike(userId: Int, trailId: Int): Hike {
        TODO("Not yet implemented")
    }

    override suspend fun getPostOverviewPage(params: TimelinePagingParams):
            TimelinePagingData.Page = server.timelineServices.get(params)

    override suspend fun getPost(postId: Int): Post {
        TODO("Not yet implemented")
    }

    override suspend fun getPostOverview(postId: Int): PostOverview {
        TODO("Not yet implemented")
    }

    override suspend fun getFeatureFlag(key: String): FeatureFlag =
        server.featureFlagServices.get(key) ?: throw NotFoundException()

    override suspend fun updateFeatureFlag(key: String, featureFlag: FeatureFlag): Boolean =
        server.featureFlagServices.put(key, featureFlag)
}