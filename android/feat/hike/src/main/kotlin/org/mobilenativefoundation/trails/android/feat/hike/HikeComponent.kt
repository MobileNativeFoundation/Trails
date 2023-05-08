package org.mobilenativefoundation.trails.android.feat.hike

import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.ContributesTo
import org.mobilenativefoundation.trails.android.common.wiring.AppScope
import org.mobilenativefoundation.trails.android.common.wiring.SingleIn
import org.mobilenativefoundation.trails.android.common.wiring.UserScope

@SingleIn(UserScope::class)
@ContributesSubcomponent(scope = UserScope::class, parentScope = AppScope::class)
interface HikeComponent {
    @ContributesSubcomponent.Factory
    interface Factory {
        fun create(): HikeComponent
    }

    @ContributesTo(AppScope::class)
    interface ParentBindings {
        fun hikeComponentFactory(): Factory
    }
}