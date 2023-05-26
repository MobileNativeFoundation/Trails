package org.mobilenativefoundation.trails.android.wiring

import com.squareup.anvil.annotations.ContributesTo
import org.mobilenativefoundation.trails.android.common.wiring.TrailsUser
import org.mobilenativefoundation.trails.android.common.wiring.UserScope
import org.mobilenativefoundation.trails.shared.timeline.TimelineRepository

@ContributesTo(UserScope::class)
interface UserDependencies {
    val timelineRepository: TimelineRepository
}