package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post

interface PostBookkeepingWriter {
    fun recordFailedDelete(key: Post.Key, timestamp: Long): Boolean
    fun recordFailedUpdate(key: Post.Key, timestamp: Long): Boolean
    fun recordFailedDeletes(keys: List<Post.Key>, timestamp: Long): Boolean
}