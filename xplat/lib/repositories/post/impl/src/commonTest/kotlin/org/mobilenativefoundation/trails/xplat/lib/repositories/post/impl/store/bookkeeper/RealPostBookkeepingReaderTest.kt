package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.eq
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import org.mobilenativefoundation.trails.xplat.lib.db.*
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.db.PostBookkeepingDAO
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class RealPostBookkeepingReaderTest {

    private val bookkeepingDAO = mock<PostBookkeepingDAO>()
    private val bookkeepingReader = RealPostBookkeepingReader(bookkeepingDAO)

    @Test
    fun findLastFailedUpdate_givenNormalBookkeepingDAOAndFailedUpdate_whenCalled_thenShouldReturnTimestamp() = runTest {
        // Given
        val failedUpdate = GetFailedUpdates(1L)

        everySuspend {
            bookkeepingDAO.getAllFailedUpdates()
        }.returns(listOf(failedUpdate))

        everySuspend {
            bookkeepingDAO.getAllFailedDeletes()
        }.returns(emptyList())

        // When
        val timestamp = bookkeepingReader.findLastFailedUpdate()

        // Then
        verifySuspend { bookkeepingDAO.getAllFailedUpdates() }
        verifySuspend { bookkeepingDAO.getAllFailedDeletes() }
        assertEquals(failedUpdate.timestamp, timestamp)
    }

    @Test
    fun findLastFailedUpdate_givenNormalBookkeepingDAOAndFailedDelete_whenCalled_thenShouldReturnTimestamp() = runTest {
        // Given
        val failedDelete = GetFailedDeletes(1L)

        everySuspend {
            bookkeepingDAO.getAllFailedUpdates()
        }.returns(emptyList())

        everySuspend {
            bookkeepingDAO.getAllFailedDeletes()
        }.returns(listOf(failedDelete))

        // When
        val timestamp = bookkeepingReader.findLastFailedUpdate()

        // Then
        verifySuspend { bookkeepingDAO.getAllFailedUpdates() }
        verifySuspend { bookkeepingDAO.getAllFailedDeletes() }
        assertEquals(failedDelete.timestamp, timestamp)
    }

    @Test
    fun findLastFailedUpdate_givenNormalBookkeepingDAOAndNoFailedSyncs_whenCalled_thenShouldReturnNull() = runTest {
        // Given

        everySuspend {
            bookkeepingDAO.getAllFailedUpdates()
        }.returns(emptyList())

        everySuspend {
            bookkeepingDAO.getAllFailedDeletes()
        }.returns(emptyList())

        // When
        val timestamp = bookkeepingReader.findLastFailedUpdate()

        // Then
        verifySuspend { bookkeepingDAO.getAllFailedUpdates() }
        verifySuspend { bookkeepingDAO.getAllFailedDeletes() }
        assertNull(timestamp)
    }

    @Test
    fun findLastFailedUpdate_givenNormalBookkeepingDAOAndFailedUpdate_whenCalledWithMultipleKeys_thenShouldReturnTimestamp() =
        runTest {
            // Given
            val keys = listOf(Post.Key(1))
            val ids = keys.map { it.id }
            val failedUpdate = GetManyFailedUpdates(1L)

            everySuspend {
                bookkeepingDAO.getManyFailedUpdates(eq(ids))
            }.returns(listOf(failedUpdate))

            everySuspend {
                bookkeepingDAO.getManyFailedDeletes(eq(ids))
            }.returns(emptyList())

            // When
            val timestamp = bookkeepingReader.findLastFailedUpdate(keys)

            // Then
            verifySuspend { bookkeepingDAO.getManyFailedUpdates(eq(ids)) }
            verifySuspend { bookkeepingDAO.getManyFailedDeletes(eq(ids)) }
            assertEquals(failedUpdate.timestamp, timestamp)
        }


    @Test
    fun findLastFailedUpdate_givenNormalBookkeepingDAOAndFailedDelete_whenCalledWithMultipleKeys_thenShouldReturnTimestamp() =
        runTest {
            // Given
            val keys = listOf(Post.Key(1))
            val ids = keys.map { it.id }
            val failedDelete = GetManyFailedDeletes(1L)

            everySuspend {
                bookkeepingDAO.getManyFailedUpdates(eq(ids))
            }.returns(emptyList())

            everySuspend {
                bookkeepingDAO.getManyFailedDeletes(eq(ids))
            }.returns(listOf(failedDelete))

            // When
            val timestamp = bookkeepingReader.findLastFailedUpdate(keys)

            // Then
            verifySuspend { bookkeepingDAO.getManyFailedUpdates(eq(ids)) }
            verifySuspend { bookkeepingDAO.getManyFailedDeletes(eq(ids)) }
            assertEquals(failedDelete.timestamp, timestamp)
        }


    @Test
    fun findLastFailedUpdate_givenNormalBookkeepingDAOAndNoFailedSyncs_whenCalledWithMultipleKeys_thenShouldReturnNull() =
        runTest {
            // Given
            val keys = listOf(Post.Key(1))
            val ids = keys.map { it.id }

            everySuspend {
                bookkeepingDAO.getManyFailedUpdates(eq(ids))
            }.returns(emptyList())

            everySuspend {
                bookkeepingDAO.getManyFailedDeletes(eq(ids))
            }.returns(emptyList())

            // When
            val timestamp = bookkeepingReader.findLastFailedUpdate(keys)

            // Then
            verifySuspend { bookkeepingDAO.getManyFailedUpdates(eq(ids)) }
            verifySuspend { bookkeepingDAO.getManyFailedDeletes(eq(ids)) }
            assertNull(timestamp)
        }

    @Test
    fun findLastFailedUpdate_givenNormalBookkeepingDAOAndFailedUpdate_whenCalledWithSingleKey_thenShouldReturnTimestamp() =
        runTest {
            // Given
            val key = Post.Key(1)
            val failedUpdate = GetOneFailedUpdate(1L)

            everySuspend {
                bookkeepingDAO.getOneFailedUpdate(key.id)
            }.returns(failedUpdate)

            everySuspend {
                bookkeepingDAO.getOneFailedDelete(key.id)
            }.returns(null)

            // When
            val timestamp = bookkeepingReader.findLastFailedUpdate(key)

            // Then
            verifySuspend { bookkeepingDAO.getOneFailedUpdate(key.id) }
            verifySuspend { bookkeepingDAO.getOneFailedDelete(key.id) }
            assertEquals(failedUpdate.timestamp, timestamp)
        }


    @Test
    fun findLastFailedUpdate_givenNormalBookkeepingDAOAndFailedDelete_whenCalledWithSingleKey_thenShouldReturnTimestamp() =
        runTest {
            // Given
            val key = Post.Key(1)
            val failedDelete = GetOneFailedDelete(1L)

            everySuspend {
                bookkeepingDAO.getOneFailedUpdate(key.id)
            }.returns(null)

            everySuspend {
                bookkeepingDAO.getOneFailedDelete(key.id)
            }.returns(failedDelete)

            // When
            val timestamp = bookkeepingReader.findLastFailedUpdate(key)

            // Then
            verifySuspend { bookkeepingDAO.getOneFailedUpdate(key.id) }
            verifySuspend { bookkeepingDAO.getOneFailedDelete(key.id) }
            assertEquals(failedDelete.timestamp, timestamp)
        }


    @Test
    fun findLastFailedUpdate_givenNormalBookkeepingDAOAndNoFailedSyncs_whenCalledWithSingleKey_thenShouldReturnNull() =
        runTest {
            // Given
            val key = Post.Key(1)

            everySuspend {
                bookkeepingDAO.getOneFailedUpdate(key.id)
            }.returns(null)

            everySuspend {
                bookkeepingDAO.getOneFailedDelete(key.id)
            }.returns(null)

            // When
            val timestamp = bookkeepingReader.findLastFailedUpdate(key)

            // Then
            verifySuspend { bookkeepingDAO.getOneFailedUpdate(key.id) }
            verifySuspend { bookkeepingDAO.getOneFailedDelete(key.id) }
            assertNull(timestamp)
        }

}