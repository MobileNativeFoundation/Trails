package org.mobilenativefoundation.trails.xplat.feat.profileScreen.impl

import androidx.compose.runtime.Composable
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.profileScreen.api.ProfileScreen

@Inject
class ProfileScreenPresenter : ProfileScreen.Presenter {

    @Composable
    override fun present(): ProfileScreen.State {
        return ProfileScreen.State
    }
}