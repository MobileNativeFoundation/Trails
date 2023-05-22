package org.mobilenativefoundation.trails.shared.paging.core

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.mobilenativefoundation.store.cache5.CacheBuilder
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.trails.shared.paging.core.utils.FakeBackendService
import org.mobilenativefoundation.trails.shared.paging.core.utils.FakePostFactory
import org.mobilenativefoundation.trails.shared.paging.core.utils.FakePostOverviewSourceOfTruth
import org.mobilenativefoundation.trails.shared.paging.core.utils.FakePostSourceOfTruth
import org.mobilenativefoundation.trails.shared.paging.core.utils.Post
import org.mobilenativefoundation.trails.shared.paging.core.utils.PostOverview
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
class PagingStoreTests {

    private val factory = FakePostFactory()
    private val testScope = TestScope()
    private lateinit var store: PagingStore<String, PostOverview, Post>

    @BeforeTest
    fun setUp() {
        val backendService = FakeBackendService()
        val postOverviewSourceOfTruth = FakePostOverviewSourceOfTruth()
        val postSourceOfTruth = FakePostSourceOfTruth()
        val pagingMemoryCache: PagingCache<String, PostOverview> = PagingCacheBuilder<String, PostOverview>().build()
        val detailMemoryCache = CacheBuilder<String, Post>().build()
        val pagingFetcher = Fetcher.of<PagingKey<String>, PagingData<String, PostOverview>> {
            when (it) {
                is PagingKey.Item -> {
                    val response = backendService.overview(it.id)
                    response?.let { overview -> PagingData.Item(overview.id, overview) } ?: throw Exception()
                }

                is PagingKey.Page -> {
                    backendService.timeline(it.params)
                }
            }
        }
        val detailFetcher = Fetcher.of<String, Post> {
            backendService.detail(it) ?: throw Exception()
        }

        store = PagingStore(
            pagingFetcher = pagingFetcher,
            detailFetcher = detailFetcher,
            pagingSourceOfTruth = postOverviewSourceOfTruth,
            detailSourceOfTruth = postSourceOfTruth,
            pagingMemoryCache = pagingMemoryCache,
            detailMemoryCache = detailMemoryCache
        )
    }

    @Test
    fun happyPath() = testScope.runTest {
        val requests: MutableSharedFlow<PagingStoreRequest.Page<String>> = MutableSharedFlow(replay = 10)

        val responses = store.flow(requests)


        val params1 = PagingParams<String>(limit = 10, after = null)
        val key1 = PagingKey.Page(params1)
        val request1 = PagingStoreRequest.Page(key1)
        requests.emit(request1)


        val first = responses.first()
        assertIs<PagingData.Page<String, PostOverview>>(first.data)

        assertEquals(
            PagingData.Page(
                params = params1,
                items = (1..10).map { factory.create(it).let { PostOverview(it.id, it.title, it.authorName) } },
                next = PagingParams(limit = 10, after = "11")
            ), first.data
        )
    }
}