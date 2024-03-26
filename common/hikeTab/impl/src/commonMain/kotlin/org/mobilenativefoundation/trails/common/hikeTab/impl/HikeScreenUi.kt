package org.mobilenativefoundation.trails.common.hikeTab.impl

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.hikeTab.api.HikeScreen

@Inject
class HikeScreenUi : HikeScreen.Ui {
    @Composable
    override fun Content(state: HikeScreen.State, modifier: Modifier) {
        Text("Hike Screen")
    }
}