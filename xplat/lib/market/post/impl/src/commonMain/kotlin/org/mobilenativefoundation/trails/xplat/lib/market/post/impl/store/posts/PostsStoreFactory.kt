package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.store.posts

import kotlinx.coroutines.flow.flow
import org.mobilenativefoundation.store.store5.Converter
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.trails.xplat.lib.db.PostHashtagEntity
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toCreatorEntity
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toHashtagEntities
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toMediaEntities
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toMentionEntities
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toPostEntity
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.CompositePostExtensions.asCompositePost
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.GetCompositePostByIdExtensions.asCreator
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.GetCompositePostByIdExtensions.asPost
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.GetCompositePostByIdExtensions.extractHashtags
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.GetCompositePostByIdExtensions.extractMedia
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.GetCompositePostByIdExtensions.extractMentions
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.insertCreatorOrIgnore
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.insertHashtagOrIgnore
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.insertMediaOrIgnore
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.insertMentionOrIgnore
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.insertPostHashtagOrIgnore
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.upsertPost
import org.mobilenativefoundation.trails.xplat.lib.models.post.*
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.PostOperations
import org.mobilenativefoundation.trails.backend.models.CompositePost as CompositePostNetworkModel

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

    private fun createFetcher(): Fetcher<String, List<CompositePostNetworkModel>> {
        return Fetcher.of {
            client.getPosts()
        }
    }

    private fun createSourceOfTruth(): SourceOfTruth<String, List<CompositePost>, List<CompositePost>> =
        SourceOfTruth.of(
            reader = { key ->
                flow {
                    val postIds = loadPostIds(key)
                    val posts = postIds.mapNotNull { postId -> assembleCompositePost(postId) }
                    emit(posts)
                }
            },
            writer = { _, populatedPosts ->
                populatedPosts.forEach { populatedPost ->
                    saveCompositePost(populatedPost)
                }
            }
        )


    private fun createConverter(): Converter<List<CompositePostNetworkModel>, List<CompositePost>, List<CompositePost>> =
        Converter.Builder<List<CompositePostNetworkModel>, List<CompositePost>, List<CompositePost>>()
            .fromOutputToLocal { it }
            .fromNetworkToLocal { populatedPosts -> populatedPosts.map { it.asCompositePost() } }
            .build()


    private fun loadPostIds(key: String): List<Long> {
        return trailsDatabase.postQueries.selectPosts("%$key%", 12).executeAsList().map { it.id }
    }

    private fun assembleCompositePost(postId: Long): CompositePost? {
        val rows = trailsDatabase.postQueries.getCompositePostById(postId).executeAsList()

        if (rows.isEmpty()) return null

        val entity = rows.first()
        val post = entity.asPost()
        val creator = entity.asCreator()
        val hashtags = rows.extractHashtags()
        val mentions = rows.extractMentions(postId)
        val media = rows.extractMedia()

        return CompositePost(post, creator, hashtags, mentions, media)
    }


    private suspend fun saveCompositePost(compositePost: CompositePost) {
        val postEntity = compositePost.toPostEntity()
        val creatorEntity = compositePost.toCreatorEntity()
        val hashtagEntities = compositePost.toHashtagEntities()
        val mentionEntities = compositePost.toMentionEntities()
        val mediaEntities = compositePost.toMediaEntities()

        trailsDatabase.postQueries.insertCreatorOrIgnore(creatorEntity)

        trailsDatabase.postQueries.upsertPost(postEntity)


        hashtagEntities.forEach {
            trailsDatabase.postQueries.insertHashtagOrIgnore(it)
            trailsDatabase.postQueries.insertPostHashtagOrIgnore(
                PostHashtagEntity(post_id = postEntity.id, hashtag_id = it.id)
            )
        }

        mentionEntities.forEach {
            trailsDatabase.postQueries.insertMentionOrIgnore(it)
        }

        mediaEntities.forEach {
            trailsDatabase.postQueries.insertMediaOrIgnore(it)
        }
    }
}


