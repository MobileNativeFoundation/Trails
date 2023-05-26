package org.mobilenativefoundation.trails.android.common.wiring

import org.mobilenativefoundation.trails.shared.data.entity.CompletedTrail
import org.mobilenativefoundation.trails.shared.data.entity.Hike
import org.mobilenativefoundation.trails.shared.data.entity.Trail
import org.mobilenativefoundation.trails.shared.data.entity.User

interface TrailsUser {
    val id: String
    val name: String
    val avatarUrl: String
    val following: List<User>
    val followedBy: List<User>
    val completedTrails: List<CompletedTrail>
    val savedTrails: List<Trail>
    val hikes: List<Hike>
}