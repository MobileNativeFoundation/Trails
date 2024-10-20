package org.mobilenativefoundation.trails.xplat.feat.postScreen.api

interface PostScreenComponent {
    val postScreenUI: PostScreen.UI
    val postScreenPresenter: PostScreen.Presenter
    val postScreen: PostScreen
}