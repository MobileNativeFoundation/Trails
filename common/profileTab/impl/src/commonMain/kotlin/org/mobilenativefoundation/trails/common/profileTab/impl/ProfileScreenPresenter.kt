package org.mobilenativefoundation.trails.common.profileTab.impl

import androidx.compose.runtime.Composable
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.profileTab.api.ProfileScreen

@Inject
class ProfileScreenPresenter : ProfileScreen.Presenter {
    @Composable
    override fun present(): ProfileScreen.State {
        return ProfileScreen.State
    }
}