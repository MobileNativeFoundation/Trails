package org.mobilenativefoundation.trails.xplat.feat.searchScreen.impl

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.searchScreen.api.SearchScreen

@Inject
class SearchScreenUI : SearchScreen.UI {
    @Composable
    override fun Content(state: SearchScreen.State, modifier: Modifier) {
        Text("Search Screen UI")
    }
}