package org.mobilenativefoundation.trails.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
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
import org.mobilenativefoundation.trails.xplat.core.circuit.api.CircuitComponent
import org.mobilenativefoundation.trails.xplat.core.circuit.impl.RealCircuitComponent
import org.mobilenativefoundation.trails.xplat.core.circuit.impl.create
import org.mobilenativefoundation.trails.xplat.feat.bottomNav.api.BottomNavComponent
import org.mobilenativefoundation.trails.xplat.feat.bottomNav.impl.RealBottomNavComponent
import org.mobilenativefoundation.trails.xplat.feat.bottomNav.impl.create
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.impl.RealHomeScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.impl.create
import org.mobilenativefoundation.trails.xplat.feat.searchScreen.api.SearchScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.searchScreen.impl.RealSearchScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.searchScreen.impl.create
import org.mobilenativefoundation.trails.xplat.lib.carve.material3.Carve


class MainActivity : ComponentActivity() {


    private val searchScreenComponent: SearchScreenComponent by lazy { RealSearchScreenComponent::class.create() }
    private val homeScreenComponent: HomeScreenComponent by lazy { RealHomeScreenComponent::class.create() }
    private val circuitComponent: CircuitComponent by lazy {
        RealCircuitComponent::class.create(homeScreenComponent, searchScreenComponent)
    }
    private val bottomNavComponent: BottomNavComponent by lazy {
        RealBottomNavComponent::class.create(
            circuitComponent
        )
    }

    private val circuit: Circuit by lazy {
        Circuit.Builder()
            .addPresenterFactory(circuitComponent.presenterFactory)
            .addUiFactory(circuitComponent.uiFactory)
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Carve {
                CircuitCompositionLocals(circuit) {

                    val selectedIndex = remember { mutableIntStateOf(0) }
                    val homeScreen = circuitComponent.screenFactory.homeScreen()
                    val bottomNavUI = bottomNavComponent.bottomNavUI
                    val activeScreen = remember { mutableStateOf<Screen>(homeScreen) }

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            bottomNavUI.Content(selectedIndex.value) { index, screen ->
                                selectedIndex.value = index
                                activeScreen.value = screen
                            }
                        }
                    ) { paddingValues ->
                        CircuitContent(
                            screen = activeScreen.value,
                            modifier = Modifier.padding(paddingValues)
                        )
                    }
                }
            }
        }
    }
}