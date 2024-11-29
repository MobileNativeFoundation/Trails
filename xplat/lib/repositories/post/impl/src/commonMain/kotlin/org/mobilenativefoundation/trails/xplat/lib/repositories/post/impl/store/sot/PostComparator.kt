package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.sot

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Order

interface PostComparator {
    fun compare(a: Post.Node, b: Post.Node, order: Order<Post.Node>?): Int
}