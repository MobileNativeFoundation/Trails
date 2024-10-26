package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.store.posts

import kotlinx.coroutines.flow.flow
import org.mobilenativefoundation.store.store5.Converter
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.trails.xplat.lib.db.GetPopulatedPostById
import org.mobilenativefoundation.trails.xplat.lib.db.PostHashtagEntity
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toCreatorEntity
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toHashtagEntities
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toMediaEntities
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toMentionEntities
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toPostEntity
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.GetPopulatedPostByIdExtensions.asCreator
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.GetPopulatedPostByIdExtensions.asPost
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostConverters.asPopulatedPost
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.insertCreatorOrIgnore
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.insertHashtagOrIgnore
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.insertMediaOrIgnore
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.insertMentionOrIgnore
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.insertPostHashtagOrIgnore
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.upsertPost
import org.mobilenativefoundation.trails.xplat.lib.models.post.*
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.PostOperations
import org.mobilenativefoundation.trails.backend.models.PopulatedPost as PopulatedPostNetworkModel

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

    private fun createFetcher(): Fetcher<String, List<PopulatedPostNetworkModel>> {
        return Fetcher.of {
            client.getPosts()
        }
    }

    private fun createSourceOfTruth(): SourceOfTruth<String, List<PopulatedPost>, List<PopulatedPost>> =
        SourceOfTruth.of(
            reader = { key ->
                flow {
                    val postIds = loadPostIds(key)
                    val posts = postIds.mapNotNull { postId -> assemblePopulatedPost(postId) }
                    emit(posts)
                }
            },
            writer = { _, populatedPosts ->
                populatedPosts.forEach { populatedPost ->
                    savePopulatedPost(populatedPost)
                }
            }
        )


    private fun createConverter(): Converter<List<PopulatedPostNetworkModel>, List<PopulatedPost>, List<PopulatedPost>> =
        Converter.Builder<List<PopulatedPostNetworkModel>, List<PopulatedPost>, List<PopulatedPost>>()
            .fromOutputToLocal { it }
            .fromNetworkToLocal { populatedPosts -> populatedPosts.map { it.asPopulatedPost() } }
            .build()


    private fun loadPostIds(key: String): List<Long> {
        return trailsDatabase.postQueries.selectPosts("%$key%", 12).executeAsList().map { it.id }
    }

    private fun assemblePopulatedPost(postId: Long): PopulatedPost? {
        val rows = trailsDatabase.postQueries.getPopulatedPostById(postId).executeAsList()

        if (rows.isEmpty()) return null

        val entity = rows.first()
        val post = entity.asPost()
        val creator = entity.asCreator()
        val hashtags = rows.extractHashtags()
        val mentions = rows.extractMentions(postId)
        val media = rows.extractMedia()

        return PopulatedPost(post, creator, hashtags, mentions, media)
    }

    private fun List<GetPopulatedPostById>.extractHashtags(): List<Hashtag> {
        return this.mapNotNull { row ->
            val id = row.hashtag_id
            val name = row.hashtag_name
            if (id != null && name != null) {
                Hashtag(id = id.toInt(), name = name)
            } else {
                null
            }
        }
    }

    private fun List<GetPopulatedPostById>.extractMentions(postId: Long): List<Mention> {
        return this.mapNotNull { row ->
            val id = row.mention_id
            val platform = row.mention_platform
            val mentionedUsername = row.mention_mentioned_username
            if (id != null && platform != null && mentionedUsername != null) {
                Mention(
                    id = id.toInt(),
                    postId = postId.toInt(),
                    mentionedUsername = mentionedUsername,
                    platform = platform
                )
            } else {
                null
            }
        }
    }

    private fun List<GetPopulatedPostById>.extractMedia(): List<Media> {
        return this.mapNotNull { row ->
            val id = row.media_id
            val mediaURL = row.media_media_url
            val mediaType = row.media_media_type
            val height = row.media_height
            val width = row.media_width
            val duration = row.media_duration
            val altText = row.media_alt_text
            val mediaFormat = row.media_media_format
            if (id != null && mediaURL != null && mediaType != null) {
                Media(
                    mediaURL = mediaURL,
                    mediaType = mediaType,
                    mediaFormat = mediaFormat,
                    duration = duration?.toInt(),
                    altText = altText,
                    height = height?.toInt(),
                    width = width?.toInt(),
                    id = id.toInt()
                )
            } else {
                null
            }
        }
    }

    private suspend fun savePopulatedPost(populatedPost: PopulatedPost) {
        val postEntity = populatedPost.toPostEntity()
        val creatorEntity = populatedPost.toCreatorEntity()
        val hashtagEntities = populatedPost.toHashtagEntities()
        val mentionEntities = populatedPost.toMentionEntities()
        val mediaEntities = populatedPost.toMediaEntities()

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


