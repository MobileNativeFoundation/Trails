package org.mobilenativefoundation.trails.common.bookmarksTab.api

interface BookmarksComponent {
    val bookmarksScreenUi: BookmarksScreen.Ui
    val bookmarksScreenPresenter: BookmarksScreen.Presenter
    val bookmarksScreen: BookmarksScreen
}