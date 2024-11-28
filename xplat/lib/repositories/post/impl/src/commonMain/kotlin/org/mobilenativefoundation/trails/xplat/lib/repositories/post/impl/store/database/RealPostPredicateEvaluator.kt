package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.query.ComparisonOperator
import org.mobilenativefoundation.trails.xplat.lib.models.query.LogicalOperator
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Predicate

class RealPostPredicateEvaluator : PostPredicateEvaluator {
    override fun evaluate(predicate: Predicate<Post.Node, *>, item: Post.Node): Boolean {
        return when (predicate) {
            is Predicate.Comparison<Post.Node, *> -> {

                when (predicate.property.get(item)) {
                    is String -> {
                        val propertyValue = predicate.property.get(item) as String
                        val comparisonValue = predicate.value as String

                        when (predicate.operator) {
                            ComparisonOperator.EQUALS -> propertyValue == comparisonValue
                            ComparisonOperator.NOT_EQUALS -> propertyValue != comparisonValue
                            ComparisonOperator.GREATER_THAN -> propertyValue > comparisonValue
                            ComparisonOperator.LESS_THAN -> propertyValue < comparisonValue
                            ComparisonOperator.GREATER_THAN_OR_EQUALS -> propertyValue >= comparisonValue
                            ComparisonOperator.LESS_THAN_OR_EQUALS -> propertyValue <= comparisonValue
                            ComparisonOperator.CONTAINS -> propertyValue.contains(comparisonValue)
                        }
                    }

                    is Boolean -> {
                        val propertyValue = predicate.property.get(item) as Boolean
                        val comparisonValue = predicate.value as Boolean

                        when (predicate.operator) {
                            ComparisonOperator.EQUALS -> propertyValue == comparisonValue
                            ComparisonOperator.NOT_EQUALS -> propertyValue != comparisonValue
                            else -> throw UnsupportedOperationException("Boolean comparison is limited to equality.")
                        }
                    }

                    is Int -> {
                        val propertyValue = predicate.property.get(item) as Int
                        val comparisonValue = predicate.value as Int

                        when (predicate.operator) {
                            ComparisonOperator.EQUALS -> propertyValue == comparisonValue
                            ComparisonOperator.NOT_EQUALS -> propertyValue != comparisonValue
                            ComparisonOperator.GREATER_THAN -> propertyValue > comparisonValue
                            ComparisonOperator.LESS_THAN -> propertyValue < comparisonValue
                            ComparisonOperator.GREATER_THAN_OR_EQUALS -> propertyValue >= comparisonValue
                            ComparisonOperator.LESS_THAN_OR_EQUALS -> propertyValue <= comparisonValue
                            ComparisonOperator.CONTAINS -> throw UnsupportedOperationException("One integer cannot contain another integer.")
                        }
                    }

                    is Long -> {
                        val propertyValue = predicate.property.get(item) as Long
                        val comparisonValue = predicate.value as Long

                        when (predicate.operator) {
                            ComparisonOperator.EQUALS -> propertyValue == comparisonValue
                            ComparisonOperator.NOT_EQUALS -> propertyValue != comparisonValue
                            ComparisonOperator.GREATER_THAN -> propertyValue > comparisonValue
                            ComparisonOperator.LESS_THAN -> propertyValue < comparisonValue
                            ComparisonOperator.GREATER_THAN_OR_EQUALS -> propertyValue >= comparisonValue
                            ComparisonOperator.LESS_THAN_OR_EQUALS -> propertyValue <= comparisonValue
                            ComparisonOperator.CONTAINS -> throw UnsupportedOperationException("One long cannot contain another long.")
                        }
                    }

                    else -> {
                        throw UnsupportedOperationException("Querying support is limited to comparisons between primitives of the same type.")
                    }
                }
            }

            is Predicate.Logical -> {
                val evaluations = predicate.predicates.map { evaluate(it, item) }
                when (predicate.operator) {
                    LogicalOperator.AND -> evaluations.all { it }
                    LogicalOperator.OR -> evaluations.any { it }
                }
            }
        }
    }

}