package org.mobilenativefoundation.trails.android

import org.mobilenativefoundation.trails.android.common.wiring.TrailsUser
import org.mobilenativefoundation.trails.shared.data.entity.User
import javax.inject.Inject



class RealTrailsUser @Inject constructor(
    user: User
) : TrailsUser {
    override val id = user.id
    override val name = user.name
    override val avatarUrl = user.avatarUrl
    override val following = user.following
    override val followedBy = user.followedBy
    override val completedTrails = user.completedTrails
    override val savedTrails = user.savedTrails
    override val hikes = user.hikes
}