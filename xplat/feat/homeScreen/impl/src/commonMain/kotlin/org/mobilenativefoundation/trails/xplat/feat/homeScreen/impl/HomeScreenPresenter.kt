package org.mobilenativefoundation.trails.xplat.feat.homeScreen.impl

import androidx.compose.runtime.*
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.backend.models.Post
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen
import org.mobilenativefoundation.trails.xplat.lib.rest.api.TrailsClient

@Inject
class HomeScreenPresenter(
    private val trailsClient: TrailsClient
) : HomeScreen.Presenter {

    @Composable
    override fun present(): HomeScreen.State {
        var posts: List<Post> by remember { mutableStateOf(listOf()) }


        LaunchedEffect(Unit) {
            posts = trailsClient.getPosts()
        }


        return HomeScreen.State(
            posts = posts.map { it.coverUrl }
        )
    }
}