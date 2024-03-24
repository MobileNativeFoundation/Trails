package org.mobilenativefoundation.trails.common.profileTab.impl

import androidx.compose.runtime.Composable
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.profileTab.api.ProfileScreen

@Inject
class ProfileScreenPresenter constructor() : ProfileScreen.Presenter {
    @Composable
    override fun invoke(): ProfileScreen.State {
        TODO("Not yet implemented")
    }
}