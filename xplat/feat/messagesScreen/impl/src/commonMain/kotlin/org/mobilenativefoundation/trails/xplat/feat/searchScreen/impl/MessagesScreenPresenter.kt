package org.mobilenativefoundation.trails.xplat.feat.searchScreen.impl

import androidx.compose.runtime.Composable
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.messagesScreen.api.MessagesScreen

@Inject
class MessagesScreenPresenter : MessagesScreen.Presenter {

    @Composable
    override fun present(): MessagesScreen.State {
        return MessagesScreen.State
    }
}