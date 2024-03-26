package org.mobilenativefoundation.trails.common.bookmarksTab.impl

import androidx.compose.runtime.Composable
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.bookmarksTab.api.BookmarksScreen

@Inject
class BookmarksScreenPresenter : BookmarksScreen.Presenter {
    @Composable
    override fun present(): BookmarksScreen.State {
        return BookmarksScreen.State
    }
}