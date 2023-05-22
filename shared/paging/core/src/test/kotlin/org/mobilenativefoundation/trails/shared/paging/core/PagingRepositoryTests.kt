package org.mobilenativefoundation.trails.shared.paging.core

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
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
import kotlin.test.assertNotNull

@OptIn(ExperimentalCoroutinesApi::class)
class PagingRepositoryTests {

    private val factory = FakePostFactory()
    private val testScope = TestScope()
    private lateinit var repository: PagingRepository<String, PostOverview, Post>

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

        repository = PagingRepository(
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
        val requests: MutableSharedFlow<PagingRepositoryRequest.Page<String>> = MutableSharedFlow(replay = 10)

        val responses = repository.flow(requests)

        val params1 = PagingParams<String>(limit = 10, after = null)
        val key1 = PagingKey.Page(params1)
        val request1 = PagingRepositoryRequest.Page(key1)
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

        val params2 = first.data.next
        assertNotNull(params2)
        assertEquals(PagingParams(limit = 10, after = "11"), params2)
        val key2 = PagingKey.Page(params2)
        val request2 = PagingRepositoryRequest.Page(key2)
        requests.emit(request2)

        val second = responses.take(2).last()
        assertIs<PagingData.Page<String, PostOverview>>(second.data)

        assertEquals(
            PagingData.Page(
                params = params2,
                items = (11..20).map { factory.create(it).let { PostOverview(it.id, it.title, it.authorName) } },
                next = PagingParams(limit = 10, after = "21")
            ), second.data
        )

        val postId = "18"
        val request3 = PagingRepositoryRequest.PagedItem(PagingKey.Item(postId))
        val item = repository.fetch(request3)
        assertEquals(
            PagingRepositoryResponse.PagedItem(
                data = PagingData.Item(
                    postId,
                    factory.create(18).let { PostOverview(it.id, it.title, it.authorName) })
            ), item
        )

        val request4 = PagingRepositoryRequest.DetailedItem(postId)
        val detail = repository.fetch(request4)
        assertEquals(PagingRepositoryResponse.DetailedItem(factory.create(18)), detail)
    }
}