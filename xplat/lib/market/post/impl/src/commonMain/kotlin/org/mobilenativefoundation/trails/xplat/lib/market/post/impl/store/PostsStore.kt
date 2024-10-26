package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.store

import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDateTime
import org.mobilenativefoundation.store.store5.*
import org.mobilenativefoundation.trails.xplat.lib.db.*
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toCreatorEntity
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toHashtagEntities
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toMediaEntities
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toMentionEntities
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toPostEntity
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.converters.PostConverters.asPopulatedPost
import org.mobilenativefoundation.trails.xplat.lib.models.post.*
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.PostOperations
import org.mobilenativefoundation.trails.backend.models.PopulatedPost as PopulatedPostNetworkModel

typealias PostsStore = Store<String, List<PopulatedPost>>


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

                    val postIds = trailsDatabase.postQueries.selectPosts("%$key%", 8).executeAsList().map { it.id }

                    val posts = mutableListOf<PopulatedPost>()

                    postIds.forEach { postId ->

                        try {

                            val rows = trailsDatabase.postQueries.getPopulatedPostById(postId).executeAsList()

                            val entity = rows[0]

                            val post = Post(
                                id = entity.post_id.toInt(),
                                creatorId = entity.post_creator_id.toInt(),
                                caption = entity.post_caption,
                                platform = entity.post_platform,
                                createdAt = LocalDateTime.parse(entity.post_created_at),
                                likesCount = entity.post_likes_count,
                                commentsCount = entity.post_comments_count,
                                sharesCount = entity.post_shares_count,
                                viewsCount = entity.post_views_count,
                                isSponsored = entity.post_is_sponsored == 1L,
                                locationName = entity.post_location_name,
                                coverURL = entity.post_cover_url,
                            )

                            val creator = Creator(
                                id = entity.creator_id?.toInt() ?: error("Missing creator ID"),
                                username = entity.creator_username ?: error("Missing creator username"),
                                fullName = entity.creator_full_name,
                                profilePicURL = entity.creator_profile_pic_url,
                                isVerified = entity.creator_is_verified == 1L,
                                bio = entity.creator_bio,
                                platform = entity.creator_platform ?: error("Missing creator platform")
                            )


                            val hashtags = mutableListOf<Hashtag>()

                            for (row in rows) {
                                val id = row.hashtag_id
                                val name = row.hashtag_name
                                if (id != null && name != null) {
                                    hashtags.add(Hashtag(id = id.toInt(), name = name))
                                }
                            }

                            val mentions = mutableListOf<Mention>()
                            for (row in rows) {
                                val id = row.mention_id
                                val platform = row.mention_platform
                                val mentionedUsername = row.mention_mentioned_username
                                if (id != null && platform != null && mentionedUsername != null) {
                                    mentions.add(
                                        Mention(
                                            id = id.toInt(),
                                            postId = postId.toInt(),
                                            mentionedUsername = mentionedUsername,
                                            platform = platform
                                        )
                                    )
                                }
                            }

                            val media = mutableListOf<Media>()
                            for (row in rows) {
                                val id = row.media_id
                                val mediaURL = row.media_media_url
                                val mediaType = row.media_media_type
                                val height = row.media_height
                                val width = row.media_width
                                val duration = row.media_duration
                                val altText = row.media_alt_text
                                val mediaFormat = row.media_media_format

                                if (id != null && mediaURL != null && mediaType != null) {
                                    media.add(
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
                                    )
                                }

                            }

                            posts.add(
                                PopulatedPost(
                                    post, creator, hashtags, mentions, media
                                )
                            )
                        } catch (_: Throwable) {
                        }

                    }

                    emit(posts)
                }
            },
            writer = { _, populatedPosts ->


                populatedPosts.forEach { populatedPost ->


                    try {

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
                    } catch (_: Throwable) {
                    }
                }
            }
        )

    private fun createConverter(): Converter<List<PopulatedPostNetworkModel>, List<PopulatedPost>, List<PopulatedPost>> =
        Converter.Builder<List<PopulatedPostNetworkModel>, List<PopulatedPost>, List<PopulatedPost>>()
            .fromOutputToLocal { it }
            .fromNetworkToLocal { populatedPosts -> populatedPosts.map { it.asPopulatedPost() } }
            .build()
}


suspend fun PostQueries.upsertPost(postEntity: PostEntity) {
    val post = findPostById(postEntity.id).executeAsOneOrNull()

    if (post == null) {
        insertPost(postEntity)
    } else {
        updatePost(
            likes_count = postEntity.likes_count,
            comments_count = postEntity.comments_count,
            shares_count = postEntity.shares_count,
            views_count = postEntity.views_count,
            id = postEntity.id
        )
    }
}


suspend fun PostQueries.insertCreatorOrIgnore(entity: CreatorEntity) {
    val creator = findCreatorById(entity.id).executeAsOneOrNull()

    if (creator == null) {
        insertCreator(entity)
    }
}

suspend fun PostQueries.insertHashtagOrIgnore(entity: HashtagEntity) {
    val hashtag = findHashtagById(entity.id).executeAsOneOrNull()

    if (hashtag == null) {
        insertHashtag(entity)
    }
}

suspend fun PostQueries.insertPostHashtagOrIgnore(entity: PostHashtagEntity) {
    val postHashtag = findPostHashtag(entity.post_id, entity.hashtag_id).executeAsOneOrNull()

    if (postHashtag == null) {
        insertPostHashtag(entity)
    }
}

suspend fun PostQueries.insertMediaOrIgnore(entity: MediaEntity) {
    val media = findMediaById(entity.id).executeAsOneOrNull()

    if (media == null) {
        insertMedia(entity)
    }
}

suspend fun PostQueries.insertMentionOrIgnore(entity: MentionEntity) {
    val mention = findMentionById(entity.id).executeAsOneOrNull()

    if (mention == null) {
        insertMention(entity)
    }
}