package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Predicate

interface PostPredicateEvaluator {
    fun evaluate(predicate: Predicate<Post.Node, *>, item: Post.Node): Boolean
}