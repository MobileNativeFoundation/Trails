package org.mobilenativefoundation.trails.common.core.impl

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.slack.circuit.foundation.CircuitContent
import com.slack.circuit.runtime.screen.Screen
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.homeTab.api.HomeScreen
import org.mobilenativefoundation.trails.common.navigation.api.BottomNavBar
import org.mobilenativefoundation.trails.common.core.api.TrailsScaffold

@Inject
class RealTrailsScaffold(
    private val bottomNavBar: BottomNavBar
) : TrailsScaffold {

    @Composable
    override fun Content(modifier: Modifier) {
        val selectedIndex = remember { mutableIntStateOf(0) }
        val activeScreen = remember { mutableStateOf<Screen>(HomeScreen) }

        Scaffold(modifier = modifier.fillMaxWidth(), bottomBar = {
            bottomNavBar.Content(selectedIndex.intValue) { index, screen ->
                selectedIndex.value = index
                activeScreen.value = screen
            }
        }) { paddingValues ->

            CircuitContent(
                screen = activeScreen.value,
                modifier = Modifier.padding(paddingValues),
            )
        }
    }
}