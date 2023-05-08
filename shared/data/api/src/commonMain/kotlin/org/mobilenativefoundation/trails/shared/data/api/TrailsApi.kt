package org.mobilenativefoundation.trails.shared.data.api

import kotlinx.coroutines.flow.Flow
import org.mobilenativefoundation.trails.shared.data.entity.Feed
import org.mobilenativefoundation.trails.shared.data.entity.Hike
import org.mobilenativefoundation.trails.shared.data.entity.Notification
import org.mobilenativefoundation.trails.shared.data.entity.Trail
import org.mobilenativefoundation.trails.shared.data.entity.User

// TODO(): Support Paging
interface TrailsApi {
    suspend fun getFeed(userId: String): Feed
    suspend fun getSavedTrails(userId: String): List<Trail>
    suspend fun getUnseenNotifications(userId: String): List<Notification>
    suspend fun getUser(userId: String): User

    // TODO(): Log In
    // TODO(): Get Feature Flags
    // TODO(): Validate Token
    // TODO(): Switch Account

    suspend fun getHike(hikeId: String): Hike
    suspend fun updateHike(hike: Hike): Hike
    fun syncHike(updates: Flow<Hike>): Flow<Hike>

    suspend fun startHike(userId: String, trailId: String): Hike
}