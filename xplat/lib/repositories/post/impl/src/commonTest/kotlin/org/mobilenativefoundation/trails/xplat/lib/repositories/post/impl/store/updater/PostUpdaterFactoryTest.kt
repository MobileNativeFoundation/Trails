package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.updater

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.test.runTest
import org.mobilenativefoundation.store.store5.Updater
import org.mobilenativefoundation.store.store5.UpdaterResult
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostWriteResponse
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.operations.query.DataSources
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostOperation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.test.utils.FakePostFactory.createCompositePost
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.test.utils.FakePostFactory.createPost
import org.mobilenativefoundation.trails.xplat.lib.rest.api.post.PostOperations
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class PostUpdaterFactoryTest {
    private val client = mock<PostOperations>()
    private val factory = PostUpdaterFactory(client)

    @Test
    fun create_whenCalled_thenShouldReturnUpdater() {
        // When
        val updater = factory.create()

        // Then
        assertIs<Updater<PostOperation, PostOutput, PostWriteResponse>>(updater)
    }

    @Test
    fun handleOperation_givenUpdater_whenCalledWithInsertOne_thenShouldMakeNetworkRequestAndReturnUpdaterResult() =
        runTest {
            // Given
            val node = createPost(1)
            val properties = node.properties
            val updater = factory.create()
            val operation = Operation.Mutation.Create.InsertOne(properties)
            everySuspend { client.insertOne(properties) }.returns(node.key)

            // When
            val result = updater.post(operation, PostOutput.Single(properties))

            // Then
            assertIs<UpdaterResult.Success.Typed<PostWriteResponse.Create>>(result)
            assertEquals(UpdaterResult.Success.Typed(PostWriteResponse.Create(node.key)), result)
        }

    @Test
    fun handleOperation_givenUpdater_whenCalledWithDeleteAll_thenShouldMakeNetworkRequestAndReturnUpdaterResult() =
        runTest {
            // Given
            val node = createPost(1)
            val properties = node.properties
            val updater = factory.create()
            val operation = Operation.Mutation.Delete.DeleteAll
            everySuspend { client.deleteAll() }.returns(1)

            // When
            val result = updater.post(operation, PostOutput.Single(properties))

            // Then
            assertIs<UpdaterResult.Success.Typed<PostWriteResponse.Delete>>(result)
            assertEquals(UpdaterResult.Success.Typed(PostWriteResponse.Delete(1)), result)
        }

    @Test
    fun handleOperation_givenUpdater_whenCalledWithDeleteMany_thenShouldMakeNetworkRequestAndReturnUpdaterResult() =
        runTest {
            // Given
            val node1 = createPost(1)
            val node2 = createPost(2)
            val updater = factory.create()
            val operation = Operation.Mutation.Delete.DeleteMany(listOf(node1.key, node2.key))
            everySuspend { client.deleteMany(listOf(node1.key, node2.key)) }.returns(2)

            // When
            val result = updater.post(operation, PostOutput.Collection(listOf(node1, node2)))

            // Then
            assertIs<UpdaterResult.Success.Typed<PostWriteResponse.Delete>>(result)
            assertEquals(UpdaterResult.Success.Typed(PostWriteResponse.Delete(2)), result)
        }


    @Test
    fun handleOperation_givenUpdater_whenCalledWithDeleteOne_thenShouldMakeNetworkRequestAndReturnUpdaterResult() =
        runTest {
            // Given
            val node1 = createPost(1)
            val updater = factory.create()
            val operation = Operation.Mutation.Delete.DeleteOne(node1.key)
            everySuspend { client.deleteOne(node1.key) }.returns(1)

            // When
            val result = updater.post(operation, PostOutput.Single(node1))

            // Then
            assertIs<UpdaterResult.Success.Typed<PostWriteResponse.Delete>>(result)
            assertEquals(UpdaterResult.Success.Typed(PostWriteResponse.Delete(1)), result)
        }

    @Test
    fun handleOperation_givenUpdater_whenCalledWithUpdateOne_thenShouldMakeNetworkRequestAndReturnUpdaterResult() =
        runTest {
            // Given
            val node1 = createPost(1)
            val updater = factory.create()
            val operation = Operation.Mutation.Update.UpdateOne(node1)
            everySuspend { client.updateOne(node1) }.returns(1)

            // When
            val result = updater.post(operation, PostOutput.Single(node1))

            // Then
            assertIs<UpdaterResult.Success.Typed<PostWriteResponse.Update>>(result)
            assertEquals(UpdaterResult.Success.Typed(PostWriteResponse.Update(1)), result)
        }

    @Test
    fun handleOperation_givenUpdater_whenCalledWithUpsertOne_thenShouldMakeNetworkRequestAndReturnUpdaterResult() =
        runTest {
            // Given
            val node1 = createPost(1)
            val updater = factory.create()
            val operation = Operation.Mutation.Upsert.UpsertOne(node1.properties)
            everySuspend { client.upsertOne(node1.properties) }.returns(node1.key)

            // When
            val result = updater.post(operation, PostOutput.Single(node1.properties))

            // Then
            assertIs<UpdaterResult.Success.Typed<PostWriteResponse.Upsert>>(result)
            assertEquals(UpdaterResult.Success.Typed(PostWriteResponse.Upsert(node1.key)), result)
        }

    @Test
    fun handleOperation_givenUpdater_whenCalledWithFindOne_thenShouldMakeNetworkRequestAndReturnUpdaterResult() =
        runTest {
            // Given
            val node1 = createPost(1)
            val updater = factory.create()
            val operation = Operation.Query.FindOne(node1.key, DataSources.all)
            everySuspend { client.updateOne(node1) }.returns(1)

            // When
            val result = updater.post(operation, PostOutput.Single(node1))

            // Then
            assertIs<UpdaterResult.Success.Typed<PostWriteResponse.Update>>(result)
            assertEquals(UpdaterResult.Success.Typed(PostWriteResponse.Update(1)), result)
        }

    @Test
    fun handleOperation_givenUpdater_whenCalledWithFindOneComposite_thenShouldMakeNetworkRequestAndReturnUpdaterResult() =
        runTest {
            // Given
            val node1 = createCompositePost(1)
            val updater = factory.create()
            val operation = Operation.Query.FindOneComposite(node1.node.key, DataSources.all)
            everySuspend { client.updateOne(node1.node) }.returns(1)

            // When
            val result = updater.post(operation, PostOutput.Single(node1))

            // Then
            assertIs<UpdaterResult.Success.Typed<PostWriteResponse.Update>>(result)
            assertEquals(UpdaterResult.Success.Typed(PostWriteResponse.Update(1)), result)
        }
}