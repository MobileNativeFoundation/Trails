package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post

interface PostBookkeepingRemover {
    fun removeFailedUpdates(keys: List<Post.Key>): Boolean
    fun removeFailedDeletes(keys: List<Post.Key>): Boolean

    fun removeAllFailedDeletes(): Boolean
    fun removeAllFailedUpdates(): Boolean

    fun removeFailedDelete(key: Post.Key): Boolean
    fun removeFailedUpdate(key: Post.Key): Boolean
}