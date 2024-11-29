package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post

interface PostBookkeepingReader {
    fun findLastFailedUpdate(): Long?
    fun findLastFailedUpdate(keys: List<Post.Key>): Long?
    fun findLastFailedUpdate(key: Post.Key): Long?
}
