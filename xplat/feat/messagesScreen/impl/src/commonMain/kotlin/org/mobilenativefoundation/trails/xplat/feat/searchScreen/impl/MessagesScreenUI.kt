package org.mobilenativefoundation.trails.xplat.feat.searchScreen.impl

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.messagesScreen.api.MessagesScreen

@Inject
class MessagesScreenUI : MessagesScreen.UI {
    @Composable
    override fun Content(state: MessagesScreen.State, modifier: Modifier) {
        Text("Messages Screen UI")
    }
}