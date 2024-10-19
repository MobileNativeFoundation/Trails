package org.mobilenativefoundation.trails.xplat.feat.messagesScreen.api

interface MessagesScreenComponent {
    val messagesScreenUI: MessagesScreen.UI
    val messagesScreenPresenter: MessagesScreen.Presenter
    val messagesScreen: MessagesScreen
}