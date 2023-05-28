package org.mobilenativefoundation.trails.shared.timeline

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.trails.shared.data.api.TrailsApi
import org.mobilenativefoundation.trails.shared.data.db.PostOverviewQueries
import org.mobilenativefoundation.trails.shared.data.db.TimelinePagingDataQueries
import org.mobilenativefoundation.trails.shared.data.db.TimelinePagingParamsQueries
import org.mobilenativefoundation.trails.shared.data.db.TimelinePostOverviewQueries
import org.mobilenativefoundation.trails.shared.data.db.translations.output
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.data.entity.paging.TimelinePagingData
import org.mobilenativefoundation.trails.shared.data.entity.paging.TimelinePagingKey
import org.mobilenativefoundation.trails.shared.data.entity.paging.TimelinePagingParams
import org.mobilenativefoundation.trails.shared.paging.core.Pager
import org.mobilenativefoundation.trails.shared.paging.core.PagerBuilder
import org.mobilenativefoundation.trails.shared.paging.core.PagingConfig
import org.mobilenativefoundation.trails.shared.paging.core.PagingConverter
import org.mobilenativefoundation.trails.shared.paging.core.PagingData
import org.mobilenativefoundation.trails.shared.paging.core.PagingKey
import org.mobilenativefoundation.trails.shared.paging.core.PagingParams
import org.mobilenativefoundation.trails.shared.paging.core.impl.PagingCache

@Suppress("UNCHECKED_CAST")
class TimelinePagerFactory(
    private val userId: Int,
    private val api: TrailsApi,
    private val paramsQueries: TimelinePagingParamsQueries,
    private val pageQueries: TimelinePagingDataQueries,
    private val postOverviewQueries: PostOverviewQueries,
    private val timelinePostOverviewQueries: TimelinePostOverviewQueries,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO),
) {
    fun create(): Pager<Int, PostOverview, PostOverview> {
        return PagerBuilder.from(
            fetcher = createFetcher(),
            sourceOfTruth = createSourceOfTruth(),
            memoryCache = createMemoryCache()
        )
            .converter(createPagingConverter())
            .config(createPagingConfig())
            .build()
    }

    private fun createFetcher() = Fetcher.of<TimelinePagingKey.Page, TimelinePagingData.Page> {
        api.getPostOverviewPage(
            TimelinePagingParams(
                it.params.loadSize,
                it.params.offset,
                it.params.type
            )
        )
    } as Fetcher<PagingKey<Int>, PagingData<Int, PostOverview, PostOverview>>

    private fun createSourceOfTruth() =
        SourceOfTruth.of<TimelinePagingKey.Page, TimelinePagingData.Page>(
            reader = { key ->
                flow {

                    val params = paramsQueries.findOne(
                        userId = userId,
                        loadSize = key.params.loadSize,
                        after = key.params.offset,
                        type = key.params.type
                    ).executeAsOneOrNull()

                    val pagingData = params?.let {
                        pageQueries
                            .findByParamsAndPopulate(params.id)
                            .executeAsList()
                            .let {
                                if (it.isNotEmpty()) {
                                    it.output()
                                } else {
                                    null
                                }
                            }
                    }

                    emit(pagingData)
                }
            },
            writer = { key, value ->

                paramsQueries.insert(
                    id = null,
                    userId = userId,
                    loadSize = key.params.loadSize,
                    after = key.params.offset,
                    type = key.params.type
                )

                val paramsId = paramsQueries.lastInsertedRowId().executeAsOne().toInt()

                pageQueries.insert(
                    id = null,
                    paramsId = paramsId,
                    next = value.next
                )

                val pageId = pageQueries.lastInsertedRowId().executeAsOne().toInt()

                value.data.forEach { postOverview ->
                    postOverviewQueries.insert(
                        id = postOverview.id,
                        userId = postOverview.userId,
                        userName = postOverview.userName,
                        userAvatarUrl = postOverview.userAvatarUrl,
                        postOverview.hikeId,
                        postOverview.title,
                        postOverview.body,
                        postOverview.coverImageUrl
                    )

                    timelinePostOverviewQueries.insert(
                        id = null,
                        pageId = pageId,
                        postOverviewId = postOverview.id
                    )
                }
            }
        ) as SourceOfTruth<PagingKey<Int>, PagingData<Int, PostOverview, PostOverview>>

    private fun createMemoryCache() = PagingCache<Int, PostOverview, PostOverview>(scope)

    private fun createPagingConverter() =
        object : PagingConverter<Int, PostOverview, PostOverview> {
            override suspend fun from(collection: PostOverview): PostOverview = collection

            override fun asPagingData(value: PostOverview): PagingData.Item<Int, PostOverview, PostOverview> =
                TimelinePagingData.Item(value.id, value)

        }

    private fun createPagingConfig() = object : PagingConfig<Int> {
        override val initialPagingKey: PagingKey<Int> = TimelinePagingKey.Page(
            params = TimelinePagingParams(
                loadSize = 50,
                offset = null,
                type = PagingParams.Type.Append
            )
        )

    }
}