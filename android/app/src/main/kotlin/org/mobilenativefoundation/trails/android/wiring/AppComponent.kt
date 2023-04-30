package org.mobilenativefoundation.trails.android.wiring

import android.content.Context
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component
import org.mobilenativefoundation.trails.android.common.wiring.AppScope
import org.mobilenativefoundation.trails.android.common.wiring.SingleIn

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
        ): AppComponent
    }
}