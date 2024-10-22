package org.mobilenativefoundation.trails.xplat.feat.homeScreen.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen
import org.mobilenativefoundation.trails.xplat.lib.carve.components.containers.VideoCard
import org.mobilenativefoundation.trails.xplat.lib.carve.material3.Carve

@Inject
class HomeScreenUI : HomeScreen.UI {
    @Composable
    override fun Content(state: HomeScreen.State, modifier: Modifier) {

        LazyColumn {
            itemsIndexed(state.posts) { index, post ->
                if (index != 0) {
                    Spacer(modifier = Modifier.fillMaxWidth().height(16.dp).background(Carve.ColorScheme.background))
                }

                VideoCard(
                    url = post,
                    creatorHandle = "netflix",
                    creatorAvatarURL = "https://pngimg.com/d/netflix_PNG22.png",
                    creatorIsVerified = true,
                    creatorDisplayName = "netflix",
                    audioSource = "Original audio"
                )
            }
        }
    }
}