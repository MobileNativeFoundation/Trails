package org.mobilenativefoundation.trails.xplat.feat.postScreen.impl

import androidx.compose.runtime.Composable
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.postScreen.api.PostScreen

@Inject
class PostScreenPresenter : PostScreen.Presenter {

    @Composable
    override fun present(): PostScreen.State {
        return PostScreen.State
    }
}