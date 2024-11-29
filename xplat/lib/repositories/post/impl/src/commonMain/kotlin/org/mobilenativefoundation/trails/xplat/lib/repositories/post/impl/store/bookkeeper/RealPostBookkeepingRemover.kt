package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post

class RealPostBookkeepingRemover(
    private val bookkeepingDAO: PostBookkeepingDAO
) : PostBookkeepingRemover {
    override fun removeFailedUpdates(keys: List<Post.Key>): Boolean {
        keys.forEach { key ->
            bookkeepingDAO.clearFailedUpdate(key.id)
        }
        return true
    }

    override fun removeFailedDeletes(keys: List<Post.Key>): Boolean {
        keys.forEach { key ->
            bookkeepingDAO.clearFailedDelete(key.id)
        }
        return true
    }

    override fun removeAllFailedDeletes(): Boolean {
        bookkeepingDAO.clearAllFailedDeletes()
        return true
    }

    override fun removeAllFailedUpdates(): Boolean {
        bookkeepingDAO.clearAllFailedUpdates()
        return true
    }

    override fun removeFailedDelete(key: Post.Key): Boolean {
        bookkeepingDAO.clearFailedDelete(key.id)
        return true
    }

    override fun removeFailedUpdate(key: Post.Key): Boolean {
        bookkeepingDAO.clearFailedUpdate(key.id)
        return true
    }

}