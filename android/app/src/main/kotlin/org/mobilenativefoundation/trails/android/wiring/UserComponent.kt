package org.mobilenativefoundation.trails.android.wiring

import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.ContributesTo
import org.mobilenativefoundation.trails.android.common.wiring.AppScope
import org.mobilenativefoundation.trails.android.common.wiring.SingleIn
import org.mobilenativefoundation.trails.android.common.wiring.UserScope

@SingleIn(UserScope::class)
@ContributesSubcomponent(scope = UserScope::class, parentScope = AppScope::class)
interface UserComponent {
    @ContributesSubcomponent.Factory
    interface Factory {
        fun create(): UserComponent
    }

    @ContributesTo(AppScope::class)
    interface ParentBindings {
        fun userComponentFactory(): Factory
    }
}