package org.mobilenativefoundation.trails.xplat.feat.searchScreen.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.xplat.feat.messagesScreen.api.MessagesScreen
import org.mobilenativefoundation.trails.xplat.feat.messagesScreen.api.MessagesScreenComponent

@Component
abstract class RealMessagesScreenComponent : MessagesScreenComponent {
    @Provides
    fun bindMessagesScreenUI(impl: MessagesScreenUI): MessagesScreen.UI = impl

    @Provides
    fun bindMessagesScreenPresenter(impl: MessagesScreenPresenter): MessagesScreen.Presenter = impl

    override val messagesScreen: MessagesScreen = RealMessagesScreen

    companion object
}