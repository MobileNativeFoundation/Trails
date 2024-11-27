package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.fetcher

import app.cash.turbine.test
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.ofType
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.mobilenativefoundation.trails.xplat.lib.models.post.Platform
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.operations.query.DataSources
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostExtensions.asPostEntity
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database.PostDAO
import org.mobilenativefoundation.trails.xplat.lib.rest.api.post.PostOperations
import kotlin.test.Test
import kotlin.test.assertEquals


class RealPostFetcherServicesTest {
    private val client = mock<PostOperations>(MockMode.autoUnit)
    private val postDAO = mock<PostDAO>(MockMode.autoUnit)
    private val postFetcherServices = RealPostFetcherServices(client, postDAO)

    @Test
    fun findAndEmitMany_givenNormalClientAndDb_whenSuccessfulNetworkRequest_thenShouldUpdateDbAndEmitOutput() =
        runTest {
            // Given
            val flow = MutableSharedFlow<PostOutput>(replay = 10)

            val operation = Operation.Query.FindMany(
                keys = listOf(Post.Key(1), Post.Key(2)),
                dataSources = DataSources.all
            )

            val post1 = createPost(1)
            val post2 = createPost(2)

            val posts = listOf(
                post1,
                post2
            )

            everySuspend { client.findMany(ofType()) }.returns(posts)

            // When
            postFetcherServices.findAndEmitMany(operation) { flow.emit(it) }

            // Then
            verifySuspend { postDAO.insertOne(post1.asPostEntity()) }
            verifySuspend { postDAO.insertOne(post2.asPostEntity()) }

            flow.test {
                val output = awaitItem()
                assertEquals(PostOutput.Collection(posts), output)
                expectNoEvents()
            }
        }

    private fun createPost(id: Int) = Post.Node(
        key = Post.Key(id),
        properties = Post.Properties(
            creatorId = 1,
            caption = id.toString(),
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            likesCount = 0,
            commentsCount = 0,
            sharesCount = 0,
            viewsCount = 0,
            isSponsored = false,
            coverURL = "",
            platform = Platform.Instagram,
            locationName = null,
        )
    )
}