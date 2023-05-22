package org.mobilenativefoundation.trails.shared.paging.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import org.mobilenativefoundation.store.cache5.Cache
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.impl.extensions.fresh
import org.mobilenativefoundation.store.store5.impl.extensions.get

class PagingStore<Id : Any, Value : Any, Detail : Any>(
    pagingFetcher: Fetcher<PagingKey<Id>, PagingData<Id, Value>>,
    detailFetcher: Fetcher<Id, Detail>,
    pagingSourceOfTruth: SourceOfTruth<PagingKey<Id>, PagingData<Id, Value>>,
    detailSourceOfTruth: SourceOfTruth<Id, Detail>,
    pagingMemoryCache: PagingCache<Id, Value>,
    detailMemoryCache: Cache<Id, Detail>
) {
    private val pagingStore = StoreBuilder.from(
        fetcher = pagingFetcher,
        sourceOfTruth = pagingSourceOfTruth,
        memoryCache = pagingMemoryCache
    ).build()

    private val detailStore = StoreBuilder.from(
        fetcher = detailFetcher,
        sourceOfTruth = detailSourceOfTruth,
        memoryCache = detailMemoryCache
    ).build()


    fun flow(requests: Flow<PagingStoreRequest.Page<Id>>): Flow<PagingStoreResponse.Page<Id, Value>> = channelFlow {
        requests.collect { request ->
            println(request)

            try {

                val page = pagingStore.fresh(request.key)
                println("page = $page")
                require(page is PagingData.Page)
                send(PagingStoreResponse.Page(page))
            } catch (error: Throwable) {
                println(error)
            }
        }
    }

    suspend fun fetch(request: PagingStoreRequest.PagedItem<Id>): PagingStoreResponse.PagedItem<Id, Value> {
        val item = pagingStore.get(request.key)
        require(item is PagingData.Item)
        return PagingStoreResponse.PagedItem(item)
    }

    suspend fun fetch(request: PagingStoreRequest.DetailedItem<Id>): PagingStoreResponse.DetailedItem<Detail> {
        val item = detailStore.get(request.key)
        return PagingStoreResponse.DetailedItem(item)
    }
}