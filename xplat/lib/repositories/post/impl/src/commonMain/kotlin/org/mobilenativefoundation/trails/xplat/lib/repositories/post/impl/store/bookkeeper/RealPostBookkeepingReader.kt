package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post

class RealPostBookkeepingReader(
    private val bookkeepingDAO: PostBookkeepingDAO
) : PostBookkeepingReader {
    override fun findLastFailedUpdate(): Long? {
        val failedUpdates = bookkeepingDAO.getAllFailedUpdates()
        val failedDeletes = bookkeepingDAO.getAllFailedDeletes()

        return when {
            failedUpdates.isEmpty() && failedDeletes.isEmpty() -> null
            failedUpdates.isNotEmpty() -> failedUpdates.first().timestamp
            else -> failedDeletes.first().timestamp
        }
    }

    override fun findLastFailedUpdate(keys: List<Post.Key>): Long? {
        val ids = keys.map { it.id }

        val failedUpdates = bookkeepingDAO.getManyFailedUpdates(ids)
        val failedDeletes = bookkeepingDAO.getManyFailedDeletes(ids)

        return when {
            failedUpdates.isEmpty() && failedDeletes.isEmpty() -> null
            failedUpdates.isNotEmpty() -> failedUpdates.first().timestamp
            else -> failedDeletes.first().timestamp
        }
    }

    override fun findLastFailedUpdate(key: Post.Key): Long? {
        val failedUpdates = bookkeepingDAO.getOneFailedUpdate(key.id)
        val failedDeletes = bookkeepingDAO.getOneFailedDelete(key.id)

        return when {
            failedUpdates == null && failedDeletes == null -> null
            failedUpdates != null -> failedUpdates.timestamp
            else -> failedDeletes?.timestamp
        }
    }

}