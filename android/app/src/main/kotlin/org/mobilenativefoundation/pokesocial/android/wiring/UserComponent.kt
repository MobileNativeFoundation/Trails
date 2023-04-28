package org.mobilenativefoundation.pokesocial.android.wiring

import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.ContributesTo
import org.mobilenativefoundation.pokesocial.android.common.wiring.AppScope
import org.mobilenativefoundation.pokesocial.android.common.wiring.SingleIn
import org.mobilenativefoundation.pokesocial.android.common.wiring.UserScope

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