package org.mobilenativefoundation.trails.shared.timeline

import kotlinx.coroutines.flow.flow
import org.mobilenativefoundation.store.cache5.CacheBuilder
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.trails.shared.data.api.TrailsApi
import org.mobilenativefoundation.trails.shared.data.db.PageQueries
import org.mobilenativefoundation.trails.shared.data.db.PostOverviewQueries
import org.mobilenativefoundation.trails.shared.data.db.PostOverviewSq
import org.mobilenativefoundation.trails.shared.data.db.PostQueries
import org.mobilenativefoundation.trails.shared.data.entity.Post
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.paging.core.PagingCacheBuilder
import org.mobilenativefoundation.trails.shared.paging.core.PagingData
import org.mobilenativefoundation.trails.shared.paging.core.PagingKey
import org.mobilenativefoundation.trails.shared.paging.core.PagingRepository

class TimelineRepositoryBuilder(
    private val userId: String,
    private val api: TrailsApi,
    private val pageQueries: PageQueries,
    private val postOverviewQueries: PostOverviewQueries,
    private val postQueries: PostQueries
) {
    fun build(): TimelineRepository {
        return PagingRepository<String, PostOverview, Post>(
            pagingFetcher = Fetcher.of {
                when (it) {
                    is PagingKey.Item -> {
                        val postOverview = api.getPostOverview(it.id)
                        PagingData.Item(id = it.id, data = postOverview)
                    }

                    is PagingKey.Page -> {
                        api.getHomeTimeline(userId, it.params.limit, it.params.after)
                    }
                }
            },
            detailFetcher = Fetcher.of {
                api.getPost(it)
            },
            pagingSourceOfTruth = SourceOfTruth.of<PagingKey<String>, PagingData<String, PostOverview>>(
                reader = { key: PagingKey<String> ->
                    flow {
                        require(key is PagingKey.Page)

                        val pageSq = pageQueries.findOne(userId, key.params.limit, key.params.after).executeAsOne()
                        emit(
                            PagingData.Page(
                                params = pageSq.params,
                                items = pageSq.itemIds.map {
                                    PostOverview(
                                        id = it,
                                        "userName_$it",
                                        "userAvatarUrl_$it",
                                        "hikeId_$it",
                                        "title_$it",
                                        "body_$it",
                                        "coverImageUrl_$it",
                                        listOf(),
                                        listOf()
                                    )
                                },
                                next = pageSq.next
                            )
                        )
                    }
                },
                writer = { key, value ->
                    require(key is PagingKey.Page)
                    require(value is PagingData.Page)


                    val items = value.items.map {
                        PostOverviewSq(
                            id = it.id,
                            userId = userId,
                            userName = it.userName,
                            userAvatarUrl = it.userAvatarUrl,
                            hikeId = it.hikeId,
                            title = it.title,
                            body = it.body,
                            coverImageUrl = it.coverImageUrl
                        )
                    }

                    items.forEach { postOverviewSq ->
                        try {
                            postOverviewQueries.insert(
                                id = postOverviewSq.id,
                                userId = postOverviewSq.userId,
                                userName = postOverviewSq.userName,
                                userAvatarUrl = postOverviewSq.userAvatarUrl,
                                hikeId = postOverviewSq.hikeId,
                                title = postOverviewSq.title,
                                body = postOverviewSq.body,
                                coverImageUrl = postOverviewSq.coverImageUrl
                            )
                        } catch (_: Throwable) {
                        }
                    }

                    try {
                        pageQueries.insert(
                            id = null,
                            params = key.params,
                            userId = userId,
                            itemLimit = key.params.limit,
                            after = key.params.after,
                            itemIds = items.map { it.id },
                            next = value.next
                        )
                    } catch (_: Throwable) {
                    }
                }
            ),
            detailSourceOfTruth = SourceOfTruth.of(
                reader = { postId ->
                    flow {
                        postQueries.findById(postId)
                    }
                },
                writer = { postId, post ->
                    postQueries.insert(
                        postId,
                        post.user.id,
                        post.user.name,
                        post.user.avatarUrl,
                        post.hike.id,
                        post.title,
                        post.body,
                        post.images[0].url
                    )
                }
            ),
            pagingMemoryCache = PagingCacheBuilder<String, PostOverview>().build(),
            detailMemoryCache = CacheBuilder<String, Post>().build()
        )
    }
}