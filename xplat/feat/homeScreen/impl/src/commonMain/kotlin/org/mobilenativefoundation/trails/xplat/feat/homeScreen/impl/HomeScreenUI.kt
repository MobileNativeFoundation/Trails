package org.mobilenativefoundation.trails.xplat.feat.homeScreen.impl

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen

@Inject
class HomeScreenUI : HomeScreen.UI {
    @Composable
    override fun Content(state: HomeScreen.State, modifier: Modifier) {

        LazyColumn {
            items(state.posts) {
                AsyncImage(
                    model = it,
                    contentDescription = null
                )
            }
        }
    }
}