package org.mobilenativefoundation.trails.shared.paging.core.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.trails.shared.paging.core.PagingData
import org.mobilenativefoundation.trails.shared.paging.core.PagingKey

class FakePostOverviewSourceOfTruth : SourceOfTruth<PagingKey<String>, PagingData<String, PostOverview>> {

    private val pages = mutableMapOf<PagingKey.Page<String>, PagingData.Page<String, PostOverview>>()
    private val items = mutableMapOf<PagingKey.Item<String>, PagingData.Item<String, PostOverview>>()

    override suspend fun delete(key: PagingKey<String>) {
        when (key) {
            is PagingKey.Item -> items.remove(key)
            is PagingKey.Page -> pages.remove(key)
        }
    }

    override suspend fun deleteAll() {
        pages.clear()
        items.clear()
    }

    override suspend fun write(key: PagingKey<String>, value: PagingData<String, PostOverview>) {
        when (key) {
            is PagingKey.Item -> {
                require(value is PagingData.Item)
                items[key] = value
            }

            is PagingKey.Page -> {
                require(value is PagingData.Page)
                pages[key] = value
            }
        }
    }

    override fun reader(key: PagingKey<String>): Flow<PagingData<String, PostOverview>?> = flow {
        when (key) {
            is PagingKey.Item -> emit(items[key])
            is PagingKey.Page -> emit(pages[key])
        }
    }

}