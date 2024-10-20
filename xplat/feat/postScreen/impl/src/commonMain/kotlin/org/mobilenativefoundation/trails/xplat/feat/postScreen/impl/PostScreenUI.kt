package org.mobilenativefoundation.trails.xplat.feat.postScreen.impl

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.postScreen.api.PostScreen

@Inject
class PostScreenUI : PostScreen.UI {
    @Composable
    override fun Content(state: PostScreen.State, modifier: Modifier) {
        Text("Post Screen UI")
    }
}