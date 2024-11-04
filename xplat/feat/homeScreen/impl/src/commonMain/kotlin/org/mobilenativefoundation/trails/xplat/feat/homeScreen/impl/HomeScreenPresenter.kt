package org.mobilenativefoundation.trails.xplat.feat.homeScreen.impl

import androidx.compose.runtime.*
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.caption
import org.mobilenativefoundation.trails.xplat.lib.models.post.id
import org.mobilenativefoundation.trails.xplat.lib.operations.query.DataSources
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.api.PostRepository


@Inject
class HomeScreenPresenter(
    private val postRepository: PostRepository
) : HomeScreen.Presenter {

    @Composable
    override fun present(): HomeScreen.State {
        var posts: List<Post.Composite> by remember { mutableStateOf(listOf()) }

        LaunchedEffect(Unit) {
            posts = postRepository.queryManyComposite {
                from(DataSources.all)
                where { Post.Node::id greaterThan  1 }
                limit(12)
                orderBy(Post.Node::id)
            }
        }

        return HomeScreen.State(
            posts = posts
        )
    }
}
