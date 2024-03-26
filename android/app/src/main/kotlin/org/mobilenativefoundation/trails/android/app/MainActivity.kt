package org.mobilenativefoundation.trails.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitContent
import com.slack.circuit.runtime.screen.Screen
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.bookmarksTab.api.BookmarksComponent
import org.mobilenativefoundation.trails.common.bookmarksTab.impl.RealBookmarksComponent
import org.mobilenativefoundation.trails.common.bookmarksTab.impl.create
import org.mobilenativefoundation.trails.common.core.api.CoreComponent
import org.mobilenativefoundation.trails.common.hikeTab.api.HikeComponent
import org.mobilenativefoundation.trails.common.hikeTab.impl.RealHikeComponent
import org.mobilenativefoundation.trails.common.hikeTab.impl.create
import org.mobilenativefoundation.trails.common.homeTab.api.HomeComponent
import org.mobilenativefoundation.trails.common.homeTab.impl.RealHomeComponent
import org.mobilenativefoundation.trails.common.homeTab.impl.create
import org.mobilenativefoundation.trails.common.navigation.api.NavigationComponent
import org.mobilenativefoundation.trails.common.navigation.impl.RealNavigationComponent
import org.mobilenativefoundation.trails.common.navigation.impl.create
import org.mobilenativefoundation.trails.common.profileTab.api.ProfileComponent
import org.mobilenativefoundation.trails.common.profileTab.impl.RealProfileComponent
import org.mobilenativefoundation.trails.common.profileTab.impl.create
import org.mobilenativefoundation.trails.common.searchTab.api.SearchComponent
import org.mobilenativefoundation.trails.common.searchTab.impl.RealSearchComponent
import org.mobilenativefoundation.trails.common.searchTab.impl.create
import org.mobilenativefoundation.trails.common.tig.compose.theme.TigTheme

@Inject
class MainActivity : ComponentActivity() {


    private val bookmarksComponent: BookmarksComponent by lazy { RealBookmarksComponent::class.create() }
    private val hikeComponent: HikeComponent by lazy { RealHikeComponent::class.create() }
    private val homeComponent: HomeComponent by lazy { RealHomeComponent::class.create() }
    private val profileComponent: ProfileComponent by lazy { RealProfileComponent::class.create() }
    private val searchComponent: SearchComponent by lazy { RealSearchComponent::class.create() }

    private val coreComponent: CoreComponent by lazy {
        RealCoreComponent.create(
            bookmarksComponent = bookmarksComponent,
            hikeComponent = hikeComponent,
            homeComponent = homeComponent,
            profileComponent = profileComponent,
            searchComponent = searchComponent,
        )
    }

    private val navigationComponent: NavigationComponent by lazy {
        RealNavigationComponent::class.create(coreComponent)
    }


    private val circuit: Circuit by lazy {
        Circuit.Builder()
            .addPresenterFactory(coreComponent.presenterFactory)
            .addUiFactory(coreComponent.uiFactory)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TigTheme {

                CircuitCompositionLocals(circuit) {

                    val modifier = Modifier
                    val selectedIndex = remember { mutableIntStateOf(0) }
                    val homeScreen = coreComponent.screenFactory.homeScreen()
                    println("HOME SCREEN = ${homeScreen}")
                    val bottomNavBar = navigationComponent.bottomNavBar

                    val activeScreen = remember { mutableStateOf<Screen>(homeScreen) }


                    Scaffold(modifier = modifier.fillMaxWidth(), bottomBar = {
                        bottomNavBar.Content(selectedIndex.intValue) { index, screen ->
                            selectedIndex.intValue = index
                            activeScreen.value = screen
                        }
                    }) { paddingValues ->

                        println("ACTIVE SCREEN = ${activeScreen.value}")
                        CircuitContent(
                            screen = activeScreen.value,
                            modifier = Modifier.padding(paddingValues),
                        )
                    }
                }
            }
        }
    }
}