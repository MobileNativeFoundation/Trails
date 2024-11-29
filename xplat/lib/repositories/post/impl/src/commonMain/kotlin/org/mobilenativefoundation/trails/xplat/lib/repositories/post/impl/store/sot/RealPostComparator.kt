package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.sot

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.query.Direction
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Order

class RealPostComparator : PostComparator {
    override fun compare(a: Post.Node, b: Post.Node, order: Order<Post.Node>?): Int {
        return if (order != null) {

            when (val type = order.property.get(a)) {
                is String -> {
                    val valueA = order.property.get(a) as String
                    val valueB = order.property.get(b) as String
                    val comparison = valueA.compareTo(valueB)
                    if (order.direction == Direction.ASC) comparison else -comparison
                }

                is Int -> {
                    val valueA = order.property.get(a) as Int
                    val valueB = order.property.get(b) as Int
                    val comparison = valueA.compareTo(valueB)
                    if (order.direction == Direction.ASC) comparison else -comparison
                }

                is Long -> {
                    val valueA = order.property.get(a) as Long
                    val valueB = order.property.get(b) as Long
                    val comparison = valueA.compareTo(valueB)
                    if (order.direction == Direction.ASC) comparison else -comparison
                }

                else -> {
                    throw UnsupportedOperationException("Received ${type?.let { it::class.simpleName }}")
                }
            }
        } else {
            0
        }
    }

}