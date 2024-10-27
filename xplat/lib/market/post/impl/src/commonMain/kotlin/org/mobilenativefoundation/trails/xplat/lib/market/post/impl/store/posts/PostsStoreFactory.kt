package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.store.posts

import kotlinx.coroutines.flow.flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.mobilenativefoundation.store.store5.Converter
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.trails.xplat.lib.db.QueryEntity
import org.mobilenativefoundation.trails.xplat.lib.db.QueryPostEntity
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.CompositePostExtensions.asCompositePost
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.insertQueryOrIgnore
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.insertQueryPostOrIgnore
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.saveCompositePost
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.PostOperations
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.PostsQuery

class PostsStoreFactory(
    private val client: PostOperations,
    private val trailsDatabase: TrailsDatabase,
) {
    fun create(): PostsStore {
        return StoreBuilder.from(
            fetcher = createFetcher(),
            sourceOfTruth = createSourceOfTruth(),
            converter = createConverter()
        ).build()
    }

    private fun createFetcher(): Fetcher<PostsQuery, List<Int>> {
        return Fetcher.of { query ->
            // Fetch composite posts from the network based on the query
            val networkCompositePosts = client.getPosts(query)

            // Save individual posts and related entities
            networkCompositePosts.forEach { networkCompositePost ->
                val compositePost = networkCompositePost.asCompositePost()
                trailsDatabase.postQueries.saveCompositePost(compositePost)
            }

            // Return list of post IDs
            networkCompositePosts.map { it.post.id }
        }
    }

    private fun createSourceOfTruth(): SourceOfTruth<PostsQuery, List<Int>, List<Int>> =
        SourceOfTruth.of(
            reader = { query ->
                flow {
                    // Query the database for post IDs matching the criteria
                    val postIds = selectPostIds(query)
                    emit(postIds)
                }
            },
            writer = { query, postIds ->
                // Save the query
                val queryId = Json.encodeToString(query)
                val queryEntity = QueryEntity(
                    id = queryId,
                    search = query.search.orEmpty(),
                    posts_offset = query.offset.toLong(),
                    posts_limit = query.limit.toLong()
                )

                trailsDatabase.postQueries.insertQueryOrIgnore(queryEntity)

                // Link each post ID to the query
                postIds.forEach { postId ->
                    trailsDatabase.postQueries.insertQueryPostOrIgnore(
                        QueryPostEntity(
                            query_id = queryId,
                            post_id = postId.toLong()
                        )
                    )
                }
            }
        )


    private fun createConverter(): Converter<List<Int>, List<Int>, List<Int>> =
        Converter.Builder<List<Int>, List<Int>, List<Int>>()
            .fromOutputToLocal { it }
            .fromNetworkToLocal { it }
            .build()


    private fun selectPostIds(query: PostsQuery): List<Int> {
        return trailsDatabase.postQueries.selectPosts("%${query.search}%", query.limit.toLong(), query.offset.toLong())
            .executeAsList().map { it.id.toInt() }
    }
}


