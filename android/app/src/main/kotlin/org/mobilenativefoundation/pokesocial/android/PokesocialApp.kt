package org.mobilenativefoundation.pokesocial.android

import android.app.Application
import com.squareup.anvil.annotations.ContributesBinding
import org.mobilenativefoundation.pokesocial.android.wiring.AppComponent
import org.mobilenativefoundation.pokesocial.android.wiring.DaggerAppComponent
import org.mobilenativefoundation.pokesocial.android.common.wiring.AppScope
import org.mobilenativefoundation.pokesocial.android.common.wiring.ComponentHolder
import org.mobilenativefoundation.pokesocial.android.common.wiring.SingleIn
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class, boundType = Application::class)
class PokesocialApp @Inject constructor() : Application(), ComponentHolder {
    override lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(applicationContext)
    }
}

