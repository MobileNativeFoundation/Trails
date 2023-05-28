package org.mobilenativefoundation.trails.shared.paging.core

import app.cash.turbine.test
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.mobilenativefoundation.trails.shared.paging.core.util.Post
import org.mobilenativefoundation.trails.shared.paging.core.util.PostPagingKey
import org.mobilenativefoundation.trails.shared.paging.core.util.PostPagingParams
import org.mobilenativefoundation.trails.shared.paging.core.util.PostPagingStoreFactory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class PagingTests {
    private val testScope = TestScope()

    @Test
    fun singleLoad() = testScope.runTest {
        val factory = PostPagingStoreFactory(this)
        val fetcher = factory.fetcher()
        val sourceOfTruth = factory.sourceOfTruth()
        val memoryCache = factory.memoryCache()

        val initialPagingParams = PostPagingParams(
            loadSize = 50,
            offset = null,
            type = PagingParams.Type.Append
        )

        val config = PagingConfig.of(PostPagingKey.Page(initialPagingParams))

        val pager = PagerBuilder.from(fetcher, sourceOfTruth, memoryCache)
            .config(config)
            .build()

        pager.state.test {
            val firstPagingState = awaitItem()
            assertIs<PagingState.Initial>(firstPagingState)

            val secondPagingState = awaitItem()
            assertIs<PagingState.Data<Int, Post, Post>>(secondPagingState)

            assertEquals(51, secondPagingState.items.size)
            assertEquals(1, secondPagingState.pages.size)
            assertEquals(initialPagingParams.copy(offset = 51), secondPagingState.next)
        }
    }

    @Test
    fun multipleLoads() = testScope.runTest {
        val factory = PostPagingStoreFactory(this)
        val fetcher = factory.fetcher()
        val sourceOfTruth = factory.sourceOfTruth()
        val memoryCache = factory.memoryCache()

        val initialPagingParams = PostPagingParams(
            loadSize = 50,
            offset = null,
            type = PagingParams.Type.Append
        )

        val config = PagingConfig.of(PostPagingKey.Page(initialPagingParams))

        val pager = PagerBuilder.from(fetcher, sourceOfTruth, memoryCache)
            .config(config)
            .build()

        fun getNextPagingKey(after: Int?) = PostPagingKey.Page(initialPagingParams.copy(offset = after))

        pager.state.test {
            val firstPagingState = awaitItem()
            assertIs<PagingState.Initial>(firstPagingState)

            val secondPagingState = awaitItem()
            assertIs<PagingState.Data<Int, Post, Post>>(secondPagingState)

            assertEquals(51, secondPagingState.items.size)
            assertEquals(1, secondPagingState.pages.size)
            assertEquals(initialPagingParams.copy(offset = 51), secondPagingState.next)

            pager.load(getNextPagingKey(secondPagingState.next?.offset))

            val thirdPagingState = awaitItem()
            assertIs<PagingState.Data<Int, Post, Post>>(thirdPagingState)

            assertEquals(102, thirdPagingState.items.size)
            assertEquals(2, thirdPagingState.pages.size)
            assertEquals(initialPagingParams.copy(offset = 102), thirdPagingState.next)

            pager.load(getNextPagingKey(thirdPagingState.next?.offset))

            val fourthPagingState = awaitItem()
            assertIs<PagingState.Data<Int, Post, Post>>(fourthPagingState)

            assertEquals(153, fourthPagingState.items.size)
            assertEquals(3, fourthPagingState.pages.size)
            assertEquals(initialPagingParams.copy(offset = 153), fourthPagingState.next)
        }
    }

}
