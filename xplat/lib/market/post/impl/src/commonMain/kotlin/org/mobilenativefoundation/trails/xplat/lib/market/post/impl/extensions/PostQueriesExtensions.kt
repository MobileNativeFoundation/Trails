package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions

import org.mobilenativefoundation.trails.xplat.lib.db.*

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
}