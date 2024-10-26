package org.mobilenativefoundation.trails.android.app

// AppComponent.kt
import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import com.slack.circuit.foundation.Circuit
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.xplat.core.circuit.api.CircuitComponent
import org.mobilenativefoundation.trails.xplat.core.circuit.impl.RealCircuitComponent
import org.mobilenativefoundation.trails.xplat.core.circuit.impl.create
import org.mobilenativefoundation.trails.xplat.feat.bottomNav.api.BottomNavComponent
import org.mobilenativefoundation.trails.xplat.feat.bottomNav.impl.RealBottomNavComponent
import org.mobilenativefoundation.trails.xplat.feat.bottomNav.impl.create
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.impl.RealHomeScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.impl.create
import org.mobilenativefoundation.trails.xplat.feat.messagesScreen.api.MessagesScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.postScreen.api.PostScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.postScreen.impl.RealPostScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.postScreen.impl.create
import org.mobilenativefoundation.trails.xplat.feat.profileScreen.api.ProfileScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.profileScreen.impl.RealProfileScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.profileScreen.impl.create
import org.mobilenativefoundation.trails.xplat.feat.searchScreen.api.SearchScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.searchScreen.impl.RealMessagesScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.searchScreen.impl.RealSearchScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.searchScreen.impl.create
import org.mobilenativefoundation.trails.xplat.lib.db.*
import org.mobilenativefoundation.trails.xplat.lib.market.post.api.PostComponent
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.RealPostComponent
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.create
import org.mobilenativefoundation.trails.xplat.lib.rest.api.TrailsClientComponent
import org.mobilenativefoundation.trails.xplat.lib.rest.impl.RealTrailsClientComponent
import org.mobilenativefoundation.trails.xplat.lib.rest.impl.create

@Component
abstract class AppComponent(@get:Provides val context: Context) {
    abstract val circuit: Circuit
    abstract val circuitComponent: CircuitComponent
    abstract val bottomNavComponent: BottomNavComponent

    @Provides
    fun provideDatabaseComponent(): DatabaseComponent = RealDatabaseComponent::class.create()

    @Provides
    fun provideDriver(): SqlDriver = DriverFactory(context).createDriver()

    @Provides
    fun provideDatabase(
        databaseComponent: DatabaseComponent,
        driver: SqlDriver
    ): TrailsDatabase = databaseComponent.trailsDatabaseFactory.create(driver)

    @Provides
    fun provideTrailsClientComponent(): TrailsClientComponent = RealTrailsClientComponent::class.create()

    @Provides
    fun providePostComponent(
        database: TrailsDatabase,
        trailsClientComponent: TrailsClientComponent
    ): PostComponent = RealPostComponent::class.create(database, trailsClientComponent)

    @Provides
    fun provideHomeScreenComponent(
        postComponent: PostComponent
    ): HomeScreenComponent = RealHomeScreenComponent::class.create(postComponent)

    @Provides
    fun provideProfileScreenComponent(): ProfileScreenComponent = RealProfileScreenComponent::class.create()

    @Provides
    fun provideMessagesScreenComponent(): MessagesScreenComponent = RealMessagesScreenComponent::class.create()

    @Provides
    fun providePostScreenComponent(): PostScreenComponent = RealPostScreenComponent::class.create()

    @Provides
    fun provideSearchScreenComponent(): SearchScreenComponent = RealSearchScreenComponent::class.create()

    @Provides
    fun provideCircuitComponent(
        homeScreenComponent: HomeScreenComponent,
        messagesScreenComponent: MessagesScreenComponent,
        postScreenComponent: PostScreenComponent,
        searchScreenComponent: SearchScreenComponent,
        profileScreenComponent: ProfileScreenComponent
    ): CircuitComponent = RealCircuitComponent::class.create(
        homeScreenComponent,
        messagesScreenComponent,
        postScreenComponent,
        searchScreenComponent,
        profileScreenComponent
    )

    @Provides
    fun provideBottomNavComponent(
        circuitComponent: CircuitComponent
    ): BottomNavComponent = RealBottomNavComponent::class.create(circuitComponent)

    @Provides
    fun provideCircuit(
        circuitComponent: CircuitComponent
    ): Circuit = Circuit.Builder()
        .addPresenterFactory(circuitComponent.presenterFactory)
        .addUiFactory(circuitComponent.uiFactory)
        .build()
}
