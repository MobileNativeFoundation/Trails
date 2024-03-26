package org.mobilenativefoundation.trails.common.searchTab.impl

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.searchTab.api.SearchScreen

@Inject
class SearchScreenUi : SearchScreen.Ui {
    @Composable
    override fun Content(state: SearchScreen.State, modifier: Modifier) {
        Text("Search Screen")
    }
}