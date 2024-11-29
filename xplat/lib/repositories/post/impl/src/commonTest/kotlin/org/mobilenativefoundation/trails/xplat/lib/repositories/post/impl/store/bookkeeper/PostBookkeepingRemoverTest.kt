package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper

import dev.mokkery.MockMode
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verifyNoMoreCalls
import kotlinx.coroutines.test.runTest
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.db.PostBookkeepingDAO
import kotlin.test.Test

class PostBookkeepingRemoverTest {
    private val bookkeepingDAO = mock<PostBookkeepingDAO>(MockMode.autoUnit)
    private val bookkeepingRemover = RealPostBookkeepingRemover(bookkeepingDAO)

    @Test
    fun removeFailedUpdates_givenValidDAOAndFailedUpdatesAndDeletes_whenCalledWithMultipleKeys_thenShouldClearFailedUpdatesOnly() =
        runTest {
            // Given
            val key1 = Post.Key(1)
            val key2 = Post.Key(2)
            val key3 = Post.Key(3)

            // When
            bookkeepingRemover.removeFailedUpdates(listOf(key1, key2, key3))

            // Then
            verify { bookkeepingDAO.clearFailedUpdate(key1.id) }
            verify { bookkeepingDAO.clearFailedUpdate(key2.id) }
            verify { bookkeepingDAO.clearFailedUpdate(key3.id) }
            verifyNoMoreCalls(bookkeepingDAO)
        }

    @Test
    fun removeFailedDeletes_givenValidDAOAndFailedUpdatesAndDeletes_whenCalledWithMultipleKeys_thenShouldClearFailedDeletesOnly() =
        runTest {
            // Given
            val key1 = Post.Key(1)
            val key2 = Post.Key(2)
            val key3 = Post.Key(3)

            // When
            bookkeepingRemover.removeFailedDeletes(listOf(key1, key2, key3))

            // Then
            verify { bookkeepingDAO.clearFailedDelete(key1.id) }
            verify { bookkeepingDAO.clearFailedDelete(key2.id) }
            verify { bookkeepingDAO.clearFailedDelete(key3.id) }
            verifyNoMoreCalls(bookkeepingDAO)
        }

    @Test
    fun removeAllFailedDeletes_givenValidDAOAndFailedUpdatesAndDeletes_whenCalled_thenShouldClearAllFailedDeletes() =
        runTest {
            // When
            bookkeepingRemover.removeAllFailedDeletes()

            // Then
            verify { bookkeepingDAO.clearAllFailedDeletes() }
            verifyNoMoreCalls(bookkeepingDAO)
        }

    @Test
    fun removeAllFailedUpdates_givenValidDAOAndFailedUpdatesAndDeletes_whenCalled_thenShouldClearAllFailedUpdates() =
        runTest {
            // When
            bookkeepingRemover.removeAllFailedUpdates()

            // Then
            verify { bookkeepingDAO.clearAllFailedUpdates() }
            verifyNoMoreCalls(bookkeepingDAO)
        }

    @Test
    fun removeFailedDelete_givenValidDAOAndFailedUpdatesAndDeletes_whenCalledWithSingleKey_thenShouldRemoveFailedDeletesForThatKey() =
        runTest {
            // Given
            val key1 = Post.Key(1)

            // When
            bookkeepingRemover.removeFailedDelete(key1)

            // Then
            verify { bookkeepingDAO.clearFailedDelete(key1.id) }
            verifyNoMoreCalls(bookkeepingDAO)
        }

    @Test
    fun removeFailedUpdate_givenValidDAOAndFailedUpdatesAndDeletes_whenCalledWithSingleKey_thenShouldRemoveFailedUpdatesForThatKey() =
        runTest {
            // Given
            val key1 = Post.Key(1)

            // When
            bookkeepingRemover.removeFailedUpdate(key1)

            // Then
            verify { bookkeepingDAO.clearFailedUpdate(key1.id) }
            verifyNoMoreCalls(bookkeepingDAO)
        }
}