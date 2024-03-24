package org.mobilenativefoundation.trails.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.bookmarksTab.api.BookmarksComponent
import org.mobilenativefoundation.trails.common.core.api.CoreComponent
import org.mobilenativefoundation.trails.common.hikeTab.api.HikeComponent
import org.mobilenativefoundation.trails.common.homeTab.api.HomeComponent
import org.mobilenativefoundation.trails.common.navigation.api.NavigationComponent
import org.mobilenativefoundation.trails.common.navigation.impl.RealNavigationComponent
import org.mobilenativefoundation.trails.common.navigation.impl.create
import org.mobilenativefoundation.trails.common.networking.api.di.NetworkingComponent
import org.mobilenativefoundation.trails.common.profileTab.api.ProfileComponent
import org.mobilenativefoundation.trails.common.searchTab.api.SearchComponent
import org.mobilenativefoundation.trails.common.tig.compose.theme.TigTheme

@Inject
class MainActivity : ComponentActivity() {


    private val component: TrailsComponent by lazy {
        object : TrailsComponent {
            override val bookmarks: BookmarksComponent
                get() = TODO()
            override val core: CoreComponent
                get() = TODO("Not yet implemented")
            override val hike: HikeComponent
                get() = TODO("Not yet implemented")
            override val home: HomeComponent
                get() = TODO("Not yet implemented")
            override val navigation: NavigationComponent = RealNavigationComponent::class.create(core)
            override val networking: NetworkingComponent
                get() = TODO()
            override val profile: ProfileComponent
                get() = TODO("Not yet implemented")
            override val search: SearchComponent
                get() = TODO("Not yet implemented")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TigTheme {
                Text(text = "Trails")
            }
        }
    }
}