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
import org.mobilenativefoundation.trails.xplat.lib.carve.material3.Carve

class MainActivity : ComponentActivity() {


    private val appComponent: AppComponent by lazy {
        (application as TrailsApp).appComponent
    }

    private val circuit: Circuit by lazy {
        Circuit.Builder()
            .addPresenterFactory(appComponent.circuitComponent.presenterFactory)
            .addUiFactory(appComponent.circuitComponent.uiFactory)
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Carve {
                CircuitCompositionLocals(circuit) {

                    val selectedIndex = remember { mutableIntStateOf(0) }
                    val homeScreen = appComponent.circuitComponent.screenFactory.homeScreen()
                    val bottomNavUI = appComponent.bottomNavComponent.bottomNavUI
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