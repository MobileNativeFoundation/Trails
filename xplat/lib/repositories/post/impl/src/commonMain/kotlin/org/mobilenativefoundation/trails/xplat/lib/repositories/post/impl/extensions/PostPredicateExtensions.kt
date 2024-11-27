package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Predicate

object PostPredicateExtensions {

    fun Predicate<Post.Node, *>.toDomain(): org.mobilenativefoundation.trails.xplat.lib.models.query.Predicate {
        return when (this) {
            is Predicate.Comparison -> {
                when (this.value) {
                    is String -> org.mobilenativefoundation.trails.xplat.lib.models.query.Predicate.Comparison.StringComparison(
                        propertyName = this.property.name.removePrefix("property "),
                        operator = this.operator,
                        value = this.value as String,
                    )

                    is Boolean -> org.mobilenativefoundation.trails.xplat.lib.models.query.Predicate.Comparison.BooleanComparison(
                        propertyName = this.property.name,
                        operator = this.operator,
                        value = this.value as Boolean,
                    )

                    is Int -> org.mobilenativefoundation.trails.xplat.lib.models.query.Predicate.Comparison.IntComparison(
                        propertyName = this.property.name.removePrefix("property "),
                        operator = this.operator,
                        value = this.value as Int,
                    )

                    else -> error("Unsupported type.")
                }


            }

            is Predicate.Logical -> {
                org.mobilenativefoundation.trails.xplat.lib.models.query.Predicate.Logical(
                    operator = this.operator,
                    predicates = this.predicates.map { it.toDomain() }
                )

            }
        }
    }
}