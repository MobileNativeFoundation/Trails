package org.mobilenativefoundation.trails.common.profileTab.impl

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.profileTab.api.ProfileScreen

@Inject
class ProfileScreenUi : ProfileScreen.Ui {
    @Composable
    override fun Content(state: ProfileScreen.State, modifier: Modifier) {
        Text("Profile Screen")
    }
}