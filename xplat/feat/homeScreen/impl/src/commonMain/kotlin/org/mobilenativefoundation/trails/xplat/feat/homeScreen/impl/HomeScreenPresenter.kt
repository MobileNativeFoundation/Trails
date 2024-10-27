package org.mobilenativefoundation.trails.xplat.feat.homeScreen.impl

import androidx.compose.runtime.*
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen
import org.mobilenativefoundation.trails.xplat.lib.market.post.api.PostRepository
import org.mobilenativefoundation.trails.xplat.lib.models.post.CompositePost
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.PostsQuery

@Inject
class HomeScreenPresenter(
    private val postRepository: PostRepository
) : HomeScreen.Presenter {

    @Composable
    override fun present(): HomeScreen.State {
        var posts: List<CompositePost> by remember { mutableStateOf(listOf()) }

        LaunchedEffect(Unit) {
            posts = postRepository.getPosts(
                PostsQuery(
                    "skiing",
                )
            )
        }

        return HomeScreen.State(
            posts = posts
        )
    }
}