package org.mobilenativefoundation.trails.shared.data.api

import kotlinx.coroutines.flow.Flow
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

// TODO(): Support Paging
interface TrailsApi {
    suspend fun getFeed(userId: Int): Feed
    suspend fun getSavedTrails(userId: Int): List<Trail>
    suspend fun getUnseenNotifications(userId: Int): List<Notification>
    suspend fun getUser(userId: Int): User

    // TODO(): Log In
    // TODO(): Get Feature Flags
    // TODO(): Validate Token
    // TODO(): Switch Account

    suspend fun getHike(hikeId: Int): Hike
    suspend fun updateHike(hike: Hike): Hike
    fun syncHike(updates: Flow<Hike>): Flow<Hike>

    suspend fun startHike(userId: Int, trailId: Int): Hike

    suspend fun getPostOverviewPage(
        params: TimelinePagingParams
    ): TimelinePagingData.Page

    suspend fun getPost(postId: Int): Post

    suspend fun getPostOverview(postId: Int): PostOverview

    suspend fun getFeatureFlag(key: String): FeatureFlag
}