package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper

import dev.mokkery.MockMode
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verifyNoMoreCalls
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.db.PostBookkeepingDAO
import kotlin.test.Test

class RealPostBookkeepingWriterTest {

    private val bookkeepingDAO = mock<PostBookkeepingDAO>(MockMode.autoUnit)
    private val bookkeepingWriter = RealPostBookkeepingWriter(bookkeepingDAO)

    @Test
    fun recordFailedDelete_givenNormalDAO_whenCalled_thenShouldInsert() {
        // Given
        val key = Post.Key(1)
        val timestamp = 1L

        // When
        bookkeepingWriter.recordFailedDelete(key, timestamp)

        // Then
        verify { bookkeepingDAO.insertFailedDelete(key.id, timestamp) }
        verifyNoMoreCalls(bookkeepingDAO)
    }

    @Test
    fun recordFailedUpdate_givenNormalDAO_whenCalled_thenShouldUpdate() {
        // Given
        val key = Post.Key(1)
        val timestamp = 1L

        // When
        bookkeepingWriter.recordFailedUpdate(key, timestamp)

        // Then
        verify { bookkeepingDAO.insertFailedUpdate(key.id, timestamp) }
        verifyNoMoreCalls(bookkeepingDAO)
    }

    @Test
    fun recordFailedDeletes_givenNormalDAO_whenCalledWithMultipleKeys_thenShouldInsertAll() {
        // Given
        val key1 = Post.Key(1)
        val key2 = Post.Key(2)
        val key3 = Post.Key(3)
        val timestamp = 1L

        // When
        bookkeepingWriter.recordFailedDeletes(listOf(key1, key2, key3), timestamp)

        // Then
        verify { bookkeepingDAO.insertFailedDelete(key1.id, timestamp) }
        verify { bookkeepingDAO.insertFailedDelete(key2.id, timestamp) }
        verify { bookkeepingDAO.insertFailedDelete(key3.id, timestamp) }
        verifyNoMoreCalls(bookkeepingDAO)
    }
}