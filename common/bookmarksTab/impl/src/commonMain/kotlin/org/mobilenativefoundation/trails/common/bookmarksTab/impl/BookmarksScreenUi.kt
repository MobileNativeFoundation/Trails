package org.mobilenativefoundation.trails.common.bookmarksTab.impl

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.bookmarksTab.api.BookmarksScreen

@Inject
class BookmarksScreenUi : BookmarksScreen.Ui {
    @Composable
    override fun Content(state: BookmarksScreen.State, modifier: Modifier) {
        Text("Bookmarks Screen")
    }
}