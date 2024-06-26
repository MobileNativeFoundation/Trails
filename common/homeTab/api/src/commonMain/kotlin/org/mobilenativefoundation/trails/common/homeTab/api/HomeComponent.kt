package org.mobilenativefoundation.trails.common.homeTab.api

interface HomeComponent {
    val homeScreenUi: HomeScreen.Ui
    val homeScreenPresenter: HomeScreen.Presenter
    val homeScreen: HomeScreen
}