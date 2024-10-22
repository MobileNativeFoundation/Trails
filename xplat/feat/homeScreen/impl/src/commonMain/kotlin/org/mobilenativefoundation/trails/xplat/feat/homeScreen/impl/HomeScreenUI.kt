package org.mobilenativefoundation.trails.xplat.feat.homeScreen.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen
import org.mobilenativefoundation.trails.xplat.lib.carve.components.containers.VideoCard
import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.CarveIcon
import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.Icon
import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.IconStyle
import org.mobilenativefoundation.trails.xplat.lib.carve.components.images.TrailsTextLogo
import org.mobilenativefoundation.trails.xplat.lib.carve.material3.Carve

@Inject
class HomeScreenUI : HomeScreen.UI {
    @Composable
    override fun Content(state: HomeScreen.State, modifier: Modifier) {

        Column {
            Surface(modifier = Modifier.fillMaxWidth(), shadowElevation = 8.dp, color = Carve.ColorScheme.background) {

                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    TrailsTextLogo(modifier = Modifier.height(50.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CarveIcon(
                            modifier = Modifier.size(28.dp),
                            icon = Icon.ACTIVITY,
                            style = IconStyle.CURVED
                        )
                    }
                }
            }

            LazyColumn {
                itemsIndexed(state.posts) { index, post ->
                    if (index != 0) {
                        Spacer(
                            modifier = Modifier.fillMaxWidth().height(16.dp).background(Carve.ColorScheme.background)
                        )
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
}