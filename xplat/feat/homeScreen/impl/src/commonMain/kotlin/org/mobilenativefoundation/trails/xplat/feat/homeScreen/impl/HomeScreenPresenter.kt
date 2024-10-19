package org.mobilenativefoundation.trails.xplat.feat.homeScreen.impl

import androidx.compose.runtime.Composable
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen

@Inject
class HomeScreenPresenter : HomeScreen.Presenter {

    @Composable
    override fun present(): HomeScreen.State {
        return HomeScreen.State
    }
}