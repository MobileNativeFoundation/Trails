package org.mobilenativefoundation.trails.shared.paging.core.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flow
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.trails.shared.paging.core.KeyGenerator
import org.mobilenativefoundation.trails.shared.paging.core.PagingConverter
import org.mobilenativefoundation.trails.shared.paging.core.PagingData
import org.mobilenativefoundation.trails.shared.paging.core.PagingKey
import org.mobilenativefoundation.trails.shared.paging.core.impl.PagingCache
import org.mobilenativefoundation.trails.shared.paging.core.util.ext.asPostPagingData

class PostPagingStoreFactory(
    private val scope: CoroutineScope,
    private val converter: PagingConverter<Int, Post, Post> = PagingConverter.default(),
    private val keyGenerator: KeyGenerator<Int, Post, Post> = KeyGenerator.default()
) {

    private val postBackendService = FakePostBackendService()
    private val database = FakeDatabase()

    private fun PagingKey.Page<Int>.asPostPagingParams() = PostPagingParams(
        loadSize = params.loadSize,
        offset = params.offset,
        type = params.type
    )

    private fun buildFetcher(): Fetcher<PagingKey<Int>, PagingData<Int, Post, Post>> =
        Fetcher.of {
            when (it) {
                is PagingKey.Item -> postBackendService.getPost(it.id)
                is PagingKey.Page -> postBackendService.getTimeline(it.asPostPagingParams())
            }
        }

    private fun buildSourceOfTruth(): SourceOfTruth<PagingKey<Int>, PagingData<Int, Post, Post>> =
        SourceOfTruth.of(
            reader = { key ->
                flow {
                    when (key) {
                        is PagingKey.Item -> database.findPostById(key.id)?.let {
                            emit(it)
                        }

                        is PagingKey.Page ->

                            emit(database.findOnePage(key.asPostPagingParams()))

                        else -> {
                            emit(null)
                        }
                    }
                }
            },
            writer = { key, value ->

                when {
                    key is PagingKey.Item && value is PagingData.Item -> {
                        database.addPost(key.id, value.asPostPagingData())
                    }

                    key is PagingKey.Page && value is PagingData.Page -> {
                        database.addPage(key.asPostPagingParams(), value.asPostPagingData())
                    }

                    else -> {
                    }
                }
            }
        )

    private fun buildMemoryCache(): PagingCache<Int, Post, Post> = PagingCache(
        scope = scope,
        converter = converter,
        keyGenerator = keyGenerator
    )

    fun build(): PostPagingStore {
        return StoreBuilder.from(
            fetcher = buildFetcher(),
            sourceOfTruth = buildSourceOfTruth(),
            memoryCache = buildMemoryCache()
        ).build()
    }

    fun fetcher() = buildFetcher()
    fun sourceOfTruth() = buildSourceOfTruth()
    fun memoryCache() = buildMemoryCache()
}
