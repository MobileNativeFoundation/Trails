package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions

import org.mobilenativefoundation.trails.xplat.lib.db.*
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toCreatorEntity
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toHashtagEntities
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toMediaEntities
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toMentionEntities
import org.mobilenativefoundation.trails.xplat.lib.db.extensions.PostExtensions.toPostEntity
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.GetCompositePostByIdExtensions.asCreator
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.GetCompositePostByIdExtensions.asPost
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.GetCompositePostByIdExtensions.extractHashtags
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.GetCompositePostByIdExtensions.extractMedia
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.GetCompositePostByIdExtensions.extractMentions
import org.mobilenativefoundation.trails.xplat.lib.models.post.CompositePost

object PostQueriesExtensions {
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

    suspend fun PostQueries.insertQueryOrIgnore(entity: QueryEntity) {
        val query = findQueryById(entity.id).executeAsOneOrNull()

        if (query == null) {
            insertQuery(entity)
        }
    }

    suspend fun PostQueries.insertQueryPostOrIgnore(entity: QueryPostEntity) {
        val queryPost = findQueryPostById(
            post_id = entity.post_id,
            query_id = entity.query_id
        ).executeAsOneOrNull()

        if (queryPost == null) {
            insertQueryPost(entity)
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

    suspend fun PostQueries.saveCompositePost(compositePost: CompositePost) {
        val postEntity = compositePost.toPostEntity()
        val creatorEntity = compositePost.toCreatorEntity()
        val hashtagEntities = compositePost.toHashtagEntities()
        val mentionEntities = compositePost.toMentionEntities()
        val mediaEntities = compositePost.toMediaEntities()

        insertCreatorOrIgnore(creatorEntity)

        upsertPost(postEntity)


        hashtagEntities.forEach {
            insertHashtagOrIgnore(it)
            insertPostHashtagOrIgnore(
                PostHashtagEntity(post_id = postEntity.id, hashtag_id = it.id)
            )
        }

        mentionEntities.forEach {
            insertMentionOrIgnore(it)
        }

        mediaEntities.forEach {
            insertMediaOrIgnore(it)
        }
    }

    fun PostQueries.assembleCompositePost(postId: Long): CompositePost? {
        val rows = getCompositePostById(postId).executeAsList()

        if (rows.isEmpty()) return null

        val entity = rows.first()
        val post = entity.asPost()
        val creator = entity.asCreator()
        val hashtags = rows.extractHashtags()
        val mentions = rows.extractMentions(postId)
        val media = rows.extractMedia()

        return CompositePost(post, creator, hashtags, mentions, media)
    }

}