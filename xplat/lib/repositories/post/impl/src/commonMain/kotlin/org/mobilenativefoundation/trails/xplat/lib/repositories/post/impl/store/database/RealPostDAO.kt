package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database

import org.mobilenativefoundation.trails.xplat.lib.db.PostEntity
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostQueriesExtensions.assembleCompositePost
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostQueriesExtensions.saveComposite

class RealPostDAO(private val trailsDatabase: TrailsDatabase) : PostDAO {
    override suspend fun insertOne(entity: PostEntity) {
        trailsDatabase.postQueries.insertPost(entity)
    }

    override suspend fun insertOneComposite(composite: Post.Composite) {
        trailsDatabase.postQueries.saveComposite(composite)
    }

    override suspend fun findOne(id: Int): PostEntity? {
        return trailsDatabase.postQueries.selectPostById(id.toLong()).executeAsOneOrNull()
    }

    override suspend fun findAll(): List<PostEntity> {
        return trailsDatabase.postQueries.findAllPosts().executeAsList()
    }

    override fun findOneAndAssembleComposite(id: Int): Post.Composite? {
        return trailsDatabase.postQueries.assembleCompositePost(id.toLong())
    }
}