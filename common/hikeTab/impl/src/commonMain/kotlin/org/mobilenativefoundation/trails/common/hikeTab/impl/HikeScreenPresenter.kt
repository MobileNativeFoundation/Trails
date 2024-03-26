package org.mobilenativefoundation.trails.common.hikeTab.impl

import androidx.compose.runtime.Composable
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.hikeTab.api.HikeScreen

@Inject
class HikeScreenPresenter : HikeScreen.Presenter {
    @Composable
    override fun present(): HikeScreen.State {
        return HikeScreen.State
    }
}