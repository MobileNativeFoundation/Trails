package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.db

import org.mobilenativefoundation.trails.xplat.lib.db.PostEntity
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post

interface PostDAO {
    suspend fun insertOne(entity: PostEntity)
    suspend fun insertOneComposite(composite: Post.Composite)
    suspend fun findOne(id: Int): PostEntity?
    suspend fun findAll(): List<PostEntity>
    fun findOneAndAssembleComposite(id: Int): Post.Composite?
}