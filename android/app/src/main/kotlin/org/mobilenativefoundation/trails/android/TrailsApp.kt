package org.mobilenativefoundation.trails.android

import android.app.Application
import com.squareup.anvil.annotations.ContributesBinding
import org.mobilenativefoundation.trails.android.wiring.AppComponent
import org.mobilenativefoundation.trails.android.wiring.DaggerAppComponent
import org.mobilenativefoundation.trails.android.common.wiring.AppScope
import org.mobilenativefoundation.trails.android.common.wiring.ComponentHolder
import org.mobilenativefoundation.trails.android.common.wiring.SingleIn
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class, boundType = Application::class)
class TrailsApp @Inject constructor() : Application(), ComponentHolder {
    override lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(applicationContext)
    }
}

