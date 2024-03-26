package org.mobilenativefoundation.trails.common.homeTab.impl

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.homeTab.api.HomeScreen

@Inject
class HomeScreenUi : HomeScreen.Ui {
    @Composable
    override fun Content(state: HomeScreen.State, modifier: Modifier) {
        Text("Home Screen UI")
    }
}