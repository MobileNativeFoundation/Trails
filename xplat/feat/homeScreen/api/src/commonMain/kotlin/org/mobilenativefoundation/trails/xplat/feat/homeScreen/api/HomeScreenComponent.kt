package org.mobilenativefoundation.trails.xplat.feat.homeScreen.api

interface HomeScreenComponent {
    val homeScreenUI: HomeScreen.UI
    val homeScreenPresenter: HomeScreen.Presenter
    val homeScreen: HomeScreen
}