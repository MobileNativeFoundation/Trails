package org.mobilenativefoundation.trails.xplat.feat.profileScreen.api

interface ProfileScreenComponent {
    val profileScreenUI: ProfileScreen.UI
    val profileScreenPresenter: ProfileScreen.Presenter
    val profileScreen: ProfileScreen
}