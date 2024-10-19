package org.mobilenativefoundation.trails.xplat.feat.searchScreen.api

interface SearchScreenComponent {
    val searchScreenUI: SearchScreen.UI
    val searchScreenPresenter: SearchScreen.Presenter
    val searchScreen: SearchScreen
}