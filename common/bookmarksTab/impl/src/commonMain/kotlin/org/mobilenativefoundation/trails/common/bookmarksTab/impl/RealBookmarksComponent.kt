package org.mobilenativefoundation.trails.common.bookmarksTab.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.common.bookmarksTab.api.BookmarksComponent
import org.mobilenativefoundation.trails.common.bookmarksTab.api.BookmarksScreen

@Component
abstract class RealBookmarksComponent : BookmarksComponent {
    @Provides
    fun bindBookmarksScreenUi(impl: BookmarksScreenUi): BookmarksScreen.Ui = impl

    @Provides
    fun bindBookmarksScreenPresenter(impl: BookmarksScreenPresenter): BookmarksScreen.Presenter = impl

    @Provides
    fun provideBookmarksScreen(): BookmarksScreen = RealBookmarksScreen

    companion object
}