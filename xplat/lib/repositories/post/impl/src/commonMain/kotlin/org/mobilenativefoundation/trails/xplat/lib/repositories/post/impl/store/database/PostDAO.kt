package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database

import kotlinx.coroutines.launch
import org.mobilenativefoundation.trails.xplat.lib.db.PostEntity
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostExtensions.asNode

interface PostDAO {
    suspend fun insertOne(entity: PostEntity)
    suspend fun insertOneComposite(composite: Post.Composite)
    suspend fun findOne(id: Int): PostEntity?
    suspend fun findAll(): List<PostEntity>
    fun findOneAndAssembleComposite(id: Int): Post.Composite?
}