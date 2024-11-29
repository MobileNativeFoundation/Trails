package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post

class RealPostBookkeepingWriter(
    private val bookkeepingDAO: PostBookkeepingDAO
) : PostBookkeepingWriter {
    override fun recordFailedDelete(key: Post.Key, timestamp: Long): Boolean {
        bookkeepingDAO.insertFailedDelete(key.id, timestamp)
        return true
    }

    override fun recordFailedUpdate(key: Post.Key, timestamp: Long): Boolean {
        bookkeepingDAO.insertFailedUpdate(key.id, timestamp)
        return true
    }

    override fun recordFailedDeletes(keys: List<Post.Key>, timestamp: Long): Boolean {
        keys.forEach { key ->
            recordFailedDelete(key, timestamp)
        }
        return true
    }

}