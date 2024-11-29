package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper

import dev.mokkery.*
import dev.mokkery.answering.returns
import kotlinx.coroutines.test.runTest
import org.mobilenativefoundation.store.store5.Bookkeeper
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.operations.query.DataSources
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Query
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Query.Many
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostOperation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.test.utils.FakePostFactory.createPost
import kotlin.test.*

class PostBookkeeperFactoryTest {

    private val bookkeepingReader = mock<PostBookkeepingReader>(MockMode.autoUnit)
    private val bookkeepingWriter = mock<PostBookkeepingWriter>(MockMode.autoUnit)
    private val bookkeepingRemover = mock<PostBookkeepingRemover>(MockMode.autoUnit)

    private val factory = PostBookkeeperFactory(bookkeepingReader, bookkeepingWriter, bookkeepingRemover)

    @Test
    fun create_whenCalled_thenShouldCreateBookkeeper() {
        // When
        val bookkeeper = factory.create()

        // Then
        assertIs<Bookkeeper<PostOperation>>(bookkeeper)
    }

    @Test
    fun handleGetLastFailedSync_givenBookkeeper_whenCalledWithFindAll_thenShouldReturnLastFailedUpdate() = runTest {
        // Given
        val lastFailedUpdate = 1L
        everySuspend { bookkeepingReader.findLastFailedUpdate() }.returns(lastFailedUpdate)
        val operation = Operation.Query.FindAll(DataSources.all)
        val bookkeeper = factory.create()

        // When
        val timestamp = bookkeeper.getLastFailedSync(operation)

        // Then
        verify { bookkeepingReader.findLastFailedUpdate() }
        assertEquals(lastFailedUpdate, timestamp)
    }

    @Test
    fun handleGetLastFailedSync_givenBookkeeper_whenCalledWithFindMany_thenShouldReturnLastFailedUpdate() = runTest {
        // Given
        val keys = listOf(Post.Key(1))
        val lastFailedUpdate = 1L
        everySuspend { bookkeepingReader.findLastFailedUpdate(keys) }.returns(lastFailedUpdate)
        val operation = Operation.Query.FindMany(keys, DataSources.all)
        val bookkeeper = factory.create()

        // When
        val timestamp = bookkeeper.getLastFailedSync(operation)

        // Then
        verify { bookkeepingReader.findLastFailedUpdate(keys) }
        assertEquals(lastFailedUpdate, timestamp)
    }

    @Test
    fun handleGetLastFailedSync_givenBookkeeper_whenCalledWithFindOne_thenShouldReturnLastFailedUpdate() = runTest {
        // Given
        val key = Post.Key(1)
        val lastFailedUpdate = 1L
        everySuspend { bookkeepingReader.findLastFailedUpdate(key) }.returns(lastFailedUpdate)
        val operation = Operation.Query.FindOne(key, DataSources.all)
        val bookkeeper = factory.create()

        // When
        val timestamp = bookkeeper.getLastFailedSync(operation)

        // Then
        verify { bookkeepingReader.findLastFailedUpdate(key) }
        assertEquals(lastFailedUpdate, timestamp)
    }

    @Test
    fun handleSetLastFailedSync_givenBookkeeper_whenCalledWithDeleteMany_thenShouldRecordAndReturnTrue() = runTest {
        // Given
        val key1 = Post.Key(1)
        val key2 = Post.Key(2)
        val operation = Operation.Mutation.Delete.DeleteMany(listOf(key1, key2))
        val bookkeeper = factory.create()
        val timestamp = 1L
        everySuspend {
            bookkeepingWriter.recordFailedDeletes(operation.keys, timestamp)
        }.returns(true)

        // When
        val response = bookkeeper.setLastFailedSync(operation, timestamp)

        // Then
        verifySuspend {
            bookkeepingWriter.recordFailedDeletes(operation.keys, timestamp)
        }

        assertTrue(response)
    }

    @Test
    fun handleSetLastFailedSync_givenBookkeeper_whenCalledWithDeleteOne_thenShouldRecordAndReturnTrue() = runTest {
        // Given
        val key1 = Post.Key(1)
        val operation = Operation.Mutation.Delete.DeleteOne(key1)
        val bookkeeper = factory.create()
        val timestamp = 1L
        everySuspend { bookkeepingWriter.recordFailedDelete(key1, timestamp) }.returns(true)

        // When
        val response = bookkeeper.setLastFailedSync(operation, timestamp)

        // Then
        verifySuspend { bookkeepingWriter.recordFailedDelete(key1, timestamp) }

        assertTrue(response)
    }

    @Test
    fun handleSetLastFailedSync_givenBookkeeper_whenCalledWithUpdateOne_thenShouldRecordAndReturnTrue() = runTest {
        // Given
        val node = createPost(1)
        val operation = Operation.Mutation.Update.UpdateOne(node)
        val bookkeeper = factory.create()
        val timestamp = 1L
        everySuspend { bookkeepingWriter.recordFailedUpdate(node.key, timestamp) }.returns(true)

        // When
        val response = bookkeeper.setLastFailedSync(operation, timestamp)

        // Then
        verifySuspend { bookkeepingWriter.recordFailedUpdate(node.key, timestamp) }

        assertTrue(response)
    }

    @Test
    fun handleClear_givenBookkeeper_whenCalledWithInsertOne_thenShouldReturnFalse() = runTest {
        // Given
        val node = createPost(1)
        val operation = Operation.Mutation.Create.InsertOne(node.properties)
        val bookkeeper = factory.create()

        // When
        val response = bookkeeper.clear(operation)

        // Then
        assertFalse(response)
    }

    @Test
    fun handleClear_givenBookkeeper_whenCalledWithDeleteAll_thenShouldRemoveAllAndReturnTrue() = runTest {
        // Given
        val operation = Operation.Mutation.Delete.DeleteAll
        val bookkeeper = factory.create()
        everySuspend { bookkeepingRemover.removeAllFailedDeletes() }.returns(true)

        // When
        val response = bookkeeper.clear(operation)

        // Then
        verifySuspend { bookkeepingRemover.removeAllFailedDeletes() }
        assertTrue(response)
    }

    @Test
    fun handleClear_givenBookkeeper_whenCalledWithDeleteMany_thenShouldRemoveAllAndReturnTrue() = runTest {
        // Given
        val key1 = Post.Key(1)
        val key2 = Post.Key(2)
        val operation = Operation.Mutation.Delete.DeleteMany(listOf(key1, key2))
        val bookkeeper = factory.create()
        everySuspend { bookkeepingRemover.removeFailedDeletes(listOf(key1, key2)) }.returns(true)

        // When
        val response = bookkeeper.clear(operation)

        // Then
        verifySuspend { bookkeepingRemover.removeFailedDeletes(listOf(key1, key2)) }
        assertTrue(response)
    }

    @Test
    fun handleClear_givenBookkeeper_whenCalledWithDeleteOne_thenShouldRemoveAndReturnTrue() = runTest {
        // Given
        val key1 = Post.Key(1)
        val operation = Operation.Mutation.Delete.DeleteOne(key1)
        val bookkeeper = factory.create()
        everySuspend { bookkeepingRemover.removeFailedDelete(key1) }.returns(true)

        // When
        val response = bookkeeper.clear(operation)

        // Then
        verifySuspend { bookkeepingRemover.removeFailedDelete(key1) }
        assertTrue(response)
    }

    @Test
    fun handleClear_givenBookkeeper_whenCalledWithReplaceOne_thenShouldReturnFalse() = runTest {
        // Given
        val node = createPost(1)
        val operation = Operation.Mutation.Update.ReplaceOne(node)
        val bookkeeper = factory.create()

        // When
        val response = bookkeeper.clear(operation)

        // Then
        assertFalse(response)
    }

    @Test
    fun handleClear_givenBookkeeper_whenCalledWithUpdateOne_thenShouldRemoveAndReturnTrue() = runTest {
        // Given
        val node = createPost(1)
        val operation = Operation.Mutation.Update.UpdateOne(node)
        val bookkeeper = factory.create()
        everySuspend { bookkeepingRemover.removeFailedUpdate(node.key) }.returns(true)

        // When
        val response = bookkeeper.clear(operation)
        verifySuspend { bookkeepingRemover.removeFailedUpdate(node.key) }

        // Then
        assertTrue(response)
    }

    @Test
    fun handleClear_givenBookkeeper_whenCalledWithUpsertOne_thenShouldReturnFalse() = runTest {
        // Given
        val node = createPost(1)
        val operation = Operation.Mutation.Upsert.UpsertOne(node.properties)
        val bookkeeper = factory.create()

        // When
        val response = bookkeeper.clear(operation)

        // Then
        assertFalse(response)
    }

    @Test
    fun handleClear_givenBookkeeper_whenCalledWithFindAllAndOnlySuccess_thenShouldRemoveAllAndReturnTrue() =
        runTest {
            // Given
            val operation = Operation.Query.FindAll(DataSources.all)
            val bookkeeper = factory.create()
            everySuspend { bookkeepingRemover.removeAllFailedUpdates() }.returns(true)
            everySuspend { bookkeepingRemover.removeAllFailedDeletes() }.returns(true)

            // When
            val response = bookkeeper.clear(operation)
            verifySuspend { bookkeepingRemover.removeAllFailedUpdates() }
            verifySuspend { bookkeepingRemover.removeAllFailedDeletes() }

            // Then
            assertTrue(response)
        }

    @Test
    fun handleClear_givenBookkeeper_whenCalledWithFindAllAndFailure_thenShouldTryToRemoveAllButReturnFalse() = runTest {
        // Given
        val operation = Operation.Query.FindAll(DataSources.all)
        val bookkeeper = factory.create()
        everySuspend { bookkeepingRemover.removeAllFailedUpdates() }.returns(false)
        everySuspend { bookkeepingRemover.removeAllFailedDeletes() }.returns(true)

        // When
        val response = bookkeeper.clear(operation)
        verifySuspend { bookkeepingRemover.removeAllFailedUpdates() }
        verifySuspend { bookkeepingRemover.removeAllFailedDeletes() }

        // Then
        assertFalse(response)
    }

    @Test
    fun handleClear_givenBookkeeper_whenCalledWithFindManyAndOnlySuccess_thenShouldRemoveAllAndReturnTrue() = runTest {
        // Given
        val key1 = Post.Key(1)
        val key2 = Post.Key(2)
        val keys = listOf(key1, key2)
        val operation = Operation.Query.FindMany(keys, DataSources.all)
        val bookkeeper = factory.create()
        everySuspend { bookkeepingRemover.removeFailedUpdates(keys) }.returns(true)
        everySuspend { bookkeepingRemover.removeFailedDeletes(keys) }.returns(true)

        // When
        val response = bookkeeper.clear(operation)
        verifySuspend { bookkeepingRemover.removeFailedUpdates(keys) }
        verifySuspend { bookkeepingRemover.removeFailedDeletes(keys) }

        // Then
        assertTrue(response)
    }


    @Test
    fun handleClear_givenBookkeeper_whenCalledWithObserveManyAndOnlySuccess_thenShouldRemoveAllAndReturnTrue() =
        runTest {
            // Given
            val key1 = Post.Key(1)
            val key2 = Post.Key(2)
            val keys = listOf(key1, key2)
            val operation = Operation.Query.ObserveMany(keys, DataSources.all)
            val bookkeeper = factory.create()
            everySuspend { bookkeepingRemover.removeFailedUpdates(keys) }.returns(true)
            everySuspend { bookkeepingRemover.removeFailedDeletes(keys) }.returns(true)

            // When
            val response = bookkeeper.clear(operation)
            verifySuspend { bookkeepingRemover.removeFailedUpdates(keys) }
            verifySuspend { bookkeepingRemover.removeFailedDeletes(keys) }

            // Then
            assertTrue(response)
        }

    @Test
    fun handleClear_givenBookkeeper_whenCalledWithFindOneAndOnlySuccess_thenShouldRemoveAndReturnTrue() = runTest {
        // Given
        val key1 = Post.Key(1)
        val operation = Operation.Query.FindOne(key1, DataSources.all)
        val bookkeeper = factory.create()
        everySuspend { bookkeepingRemover.removeFailedUpdate(key1) }.returns(true)
        everySuspend { bookkeepingRemover.removeFailedDelete(key1) }.returns(true)

        // When
        val response = bookkeeper.clear(operation)
        verifySuspend { bookkeepingRemover.removeFailedUpdate(key1) }
        verifySuspend { bookkeepingRemover.removeFailedDelete(key1) }

        // Then
        assertTrue(response)
    }


    @Test
    fun handleClear_givenBookkeeper_whenCalledWithFindOneCompositeAndOnlySuccess_thenShouldRemoveAndReturnTrue() =
        runTest {
            // Given
            val key1 = Post.Key(1)
            val operation = Operation.Query.FindOneComposite(key1, DataSources.all)
            val bookkeeper = factory.create()
            everySuspend { bookkeepingRemover.removeFailedUpdate(key1) }.returns(true)
            everySuspend { bookkeepingRemover.removeFailedDelete(key1) }.returns(true)

            // When
            val response = bookkeeper.clear(operation)
            verifySuspend { bookkeepingRemover.removeFailedUpdate(key1) }
            verifySuspend { bookkeepingRemover.removeFailedDelete(key1) }

            // Then
            assertTrue(response)
        }


    @Test
    fun handleClear_givenBookkeeper_whenCalledWithObserveOneAndOnlySuccess_thenShouldRemoveAndReturnTrue() = runTest {
        // Given
        val key1 = Post.Key(1)
        val operation = Operation.Query.ObserveOne(key1, DataSources.all)
        val bookkeeper = factory.create()
        everySuspend { bookkeepingRemover.removeFailedUpdate(key1) }.returns(true)
        everySuspend { bookkeepingRemover.removeFailedDelete(key1) }.returns(true)

        // When
        val response = bookkeeper.clear(operation)
        verifySuspend { bookkeepingRemover.removeFailedUpdate(key1) }
        verifySuspend { bookkeepingRemover.removeFailedDelete(key1) }

        // Then
        assertTrue(response)
    }

    @Test
    fun handleClear_givenBookkeeper_whenCalledWithObserveOneCompositeAndOnlySuccess_thenShouldRemoveAndReturnTrue() =
        runTest {
            // Given
            val key1 = Post.Key(1)
            val operation = Operation.Query.ObserveOneComposite(key1, DataSources.all)
            val bookkeeper = factory.create()
            everySuspend { bookkeepingRemover.removeFailedUpdate(key1) }.returns(true)
            everySuspend { bookkeepingRemover.removeFailedDelete(key1) }.returns(true)

            // When
            val response = bookkeeper.clear(operation)
            verifySuspend { bookkeepingRemover.removeFailedUpdate(key1) }
            verifySuspend { bookkeepingRemover.removeFailedDelete(key1) }

            // Then
            assertTrue(response)
        }


    @Test
    fun handleClear_givenBookkeeper_whenCalledWithQueryMany_thenShouldReturnFalse() =
        runTest {
            // Given

            val query = Many<Post.Node>(
                dataSources = DataSources.all,
                predicate = null,
                order = null,
                limit = null
            )

            val operation = Operation.Query.QueryMany(query, DataSources.all)
            val bookkeeper = factory.create()

            // When
            val response = bookkeeper.clear(operation)

            // Then
            assertFalse(response)
        }

    @Test
    fun handleClear_givenBookkeeper_whenCalledWithQueryManyComposite_thenShouldReturnFalse() =
        runTest {
            // Given

            val query = Many<Post.Node>(
                dataSources = DataSources.all,
                predicate = null,
                order = null,
                limit = null
            )

            val operation = Operation.Query.QueryManyComposite(query, DataSources.all)
            val bookkeeper = factory.create()

            // When
            val response = bookkeeper.clear(operation)

            // Then
            assertFalse(response)
        }

    @Test
    fun handleClear_givenBookkeeper_whenCalledWithQueryOne_thenShouldReturnFalse() =
        runTest {
            // Given

            val query = Query.One<Post.Node>(
                dataSources = DataSources.all,
                predicate = null,
                order = null,
            )

            val operation = Operation.Query.QueryOne(query, DataSources.all)
            val bookkeeper = factory.create()

            // When
            val response = bookkeeper.clear(operation)

            // Then
            assertFalse(response)
        }

}