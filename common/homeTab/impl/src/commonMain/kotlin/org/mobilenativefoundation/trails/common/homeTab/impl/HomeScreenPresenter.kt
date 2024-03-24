package org.mobilenativefoundation.trails.common.homeTab.impl

import androidx.compose.runtime.Composable
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.homeTab.api.HomeScreen

@Inject
class HomeScreenPresenter constructor() : HomeScreen.Presenter {
    @Composable
    override operator fun invoke(): HomeScreen.State {
        TODO("Not yet implemented")
    }
}