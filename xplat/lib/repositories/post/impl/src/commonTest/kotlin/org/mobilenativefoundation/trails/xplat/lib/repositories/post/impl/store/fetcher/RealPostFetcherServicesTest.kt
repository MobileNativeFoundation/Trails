package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.fetcher

import app.cash.turbine.test
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.eq
import dev.mokkery.matcher.ofType
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.runTest
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation.Query.QueryMany
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation.Query.QueryOne
import org.mobilenativefoundation.trails.xplat.lib.operations.query.DataSources
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Query
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostExtensions.asPostEntity
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database.PostDAO
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.test_utils.FakePostFactory.createCompositePost
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.test_utils.FakePostFactory.createPost
import org.mobilenativefoundation.trails.xplat.lib.rest.api.post.PostOperations
import kotlin.test.Test
import kotlin.test.assertEquals


class RealPostFetcherServicesTest {
    private val client = mock<PostOperations>(MockMode.autoUnit)
    private val postDAO = mock<PostDAO>(MockMode.autoUnit)
    private val postFetcherServices = RealPostFetcherServices(client, postDAO)

    @Test
    fun findAndEmitOne_givenNormalClientAndDb_whenSuccessfulNetworkRequest_thenShouldUpdateDbAndEmitOutput() = runTest {
        // Given
        val flow = MutableSharedFlow<PostOutput>(replay = 10)

        val operation = Operation.Query.FindOne(
            key = Post.Key(1),
            dataSources = DataSources.all
        )

        val post1 = createPost(1)

        everySuspend { client.findOne(eq(operation.key)) }.returns(post1)

        // When
        postFetcherServices.findAndEmitOne(operation) { flow.emit(it) }

        // Then
        verifySuspend { postDAO.insertOne(post1.asPostEntity()) }

        flow.test {
            val output = awaitItem()
            assertEquals(PostOutput.Single(post1), output)
            expectNoEvents()
        }
    }

    @Test
    fun findAndEmitOneComposite_givenNormalClientAndDb_whenSuccessfulNetworkRequest_thenShouldUpdateDbAndEmitOutput() =
        runTest {
            // Given
            val flow = MutableSharedFlow<PostOutput>(replay = 10)

            val operation = Operation.Query.FindOneComposite(
                key = Post.Key(1),
                dataSources = DataSources.all
            )

            val compositePost = createCompositePost(1)

            everySuspend { client.findOneComposite(eq(operation.key)) }.returns(compositePost)

            // When
            postFetcherServices.findAndEmitOneComposite(operation) { flow.emit(it) }

            // Then
            verifySuspend { postDAO.insertOneComposite(compositePost) }

            flow.test {
                val output = awaitItem()
                assertEquals(PostOutput.Single(compositePost), output)
                expectNoEvents()
            }
        }

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

    @Test
    fun findAndEmitAll_givenNormalClientAndDb_whenSuccessfulNetworkRequest_thenShouldUpdateDbAndEmitOutput() =
        runTest {
            // Given
            val flow = MutableSharedFlow<PostOutput>(replay = 10)

            val operation = Operation.Query.FindAll(
                dataSources = DataSources.all
            )

            val post1 = createPost(1)
            val post2 = createPost(2)

            val posts = listOf(
                post1,
                post2
            )

            everySuspend { client.findAll() }.returns(posts)

            // When
            postFetcherServices.findAndEmitAll(operation) { flow.emit(it) }

            // Then
            verifySuspend { postDAO.insertOne(post1.asPostEntity()) }
            verifySuspend { postDAO.insertOne(post2.asPostEntity()) }

            flow.test {
                val output = awaitItem()
                assertEquals(PostOutput.Collection(posts), output)
                expectNoEvents()
            }
        }

    @Test
    fun queryAndEmitOne_givenNormalClientAndDb_whenSuccessfulNetworkRequest_thenShouldUpdateDbAndEmitOutput() =
        runTest {
            // Given
            val flow = MutableSharedFlow<PostOutput>(replay = 10)

            val operation = QueryOne(
                query = Query.One<Post.Node>(
                    dataSources = DataSources.all,
                    predicate = null,
                    order = null
                ),
                dataSources = DataSources.all
            )

            val post1 = createPost(1)

            everySuspend { client.queryOne(ofType()) }.returns(post1)

            // When
            postFetcherServices.queryAndEmitOne(operation) { flow.emit(it) }

            // Then
            verifySuspend { postDAO.insertOne(post1.asPostEntity()) }

            flow.test {
                val output = awaitItem()
                assertEquals(PostOutput.Single(post1), output)
                expectNoEvents()
            }
        }

    @Test
    fun queryAndEmitMany_givenNormalClientAndDb_whenSuccessfulNetworkRequest_thenShouldUpdateDbAndEmitOutput() =
        runTest {
            // Given
            val flow = MutableSharedFlow<PostOutput>(replay = 10)

            val operation = QueryMany(
                query = Query.Many<Post.Node>(
                    dataSources = DataSources.all,
                    predicate = null,
                    order = null,
                    limit = null
                ),
                dataSources = DataSources.all
            )

            val post1 = createPost(1)
            val post2 = createPost(2)

            val posts = listOf(
                post1,
                post2
            )

            everySuspend { client.queryMany(ofType()) }.returns(posts)

            // When
            postFetcherServices.queryAndEmitMany(operation) { flow.emit(it) }

            // Then
            verifySuspend { postDAO.insertOne(post1.asPostEntity()) }
            verifySuspend { postDAO.insertOne(post2.asPostEntity()) }

            flow.test {
                val output = awaitItem()
                assertEquals(PostOutput.Collection(posts), output)
                expectNoEvents()
            }
        }


    @Test
    fun queryAndEmitManyComposite_givenNormalClientAndDb_whenSuccessfulNetworkRequest_thenShouldUpdateDbAndEmitOutput() =
        runTest {
            // Given
            val flow = MutableSharedFlow<PostOutput>(replay = 10)

            val operation = Operation.Query.QueryManyComposite(
                query = Query.Many<Post.Node>(
                    dataSources = DataSources.all,
                    predicate = null,
                    order = null,
                    limit = null
                ),
                dataSources = DataSources.all
            )

            val post1 = createCompositePost(1)
            val post2 = createCompositePost(2)

            val posts = listOf(
                post1,
                post2
            )

            everySuspend { client.queryManyComposite(ofType()) }.returns(PostOutput.Collection(posts))

            // When
            postFetcherServices.queryAndEmitManyComposite(operation) { flow.emit(it) }

            // Then
            verifySuspend { postDAO.insertOneComposite(post1) }
            verifySuspend { postDAO.insertOneComposite(post2) }

            flow.test {
                val output = awaitItem()
                assertEquals(PostOutput.Collection(posts), output)
                expectNoEvents()
            }
        }
}