package org.mobilenativefoundation.trails.common.hikeTab.api

interface HikeComponent {
    val hikeScreenUi: HikeScreen.Ui
    val hikeScreenPresenter: HikeScreen.Presenter
    val hikeScreen: HikeScreen
}