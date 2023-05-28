package org.mobilenativefoundation.trails.android

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.squareup.anvil.annotations.ContributesBinding
import org.mobilenativefoundation.trails.android.common.wiring.AppScope
import org.mobilenativefoundation.trails.android.common.wiring.ComponentHolder
import org.mobilenativefoundation.trails.android.common.wiring.SingleIn
import org.mobilenativefoundation.trails.android.wiring.AppComponent
import org.mobilenativefoundation.trails.android.wiring.DaggerAppComponent
import org.mobilenativefoundation.trails.db.TrailsDb
import org.mobilenativefoundation.trails.shared.data.entity.User
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class, boundType = Application::class)
class TrailsApp @Inject constructor() : Application(), ComponentHolder {
    override lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()


        val driver: SqlDriver =
            AndroidSqliteDriver(TrailsDb.Schema, applicationContext, "trails.db")

        component = DaggerAppComponent.factory().create(
            applicationContext,
            driver,
            User(
                1,
                "name",
                "avatarUrl",
                listOf(),
                listOf(),
                listOf(),
                listOf(),
                listOf()
            )
        )
    }
}

