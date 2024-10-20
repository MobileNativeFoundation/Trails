package org.mobilenativefoundation.trails.xplat.feat.profileScreen.impl

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.profileScreen.api.ProfileScreen

@Inject
class ProfileScreenUI : ProfileScreen.UI {
    @Composable
    override fun Content(state: ProfileScreen.State, modifier: Modifier) {
        Text("Profile Screen UI")
    }
}