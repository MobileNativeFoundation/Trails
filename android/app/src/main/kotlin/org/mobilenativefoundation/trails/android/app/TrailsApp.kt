package org.mobilenativefoundation.trails.android.app

import android.app.Application


class TrailsApp : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent::class.create(applicationContext)
    }
}
