package org.mobilenativefoundation.trails.xplat.feat.homeScreen.impl

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen

@Inject
class HomeScreenUI : HomeScreen.UI {
    @Composable
    override fun Content(state: HomeScreen.State, modifier: Modifier) {
        Text("Home Screen UI")
    }
}