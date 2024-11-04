package org.mobilenativefoundation.trails.xplat.lib.models.query

import kotlinx.serialization.Serializable

@Serializable
sealed class Predicate {

    @Serializable
    sealed class Comparison : Predicate() {
        abstract val propertyName: String
        abstract val operator: ComparisonOperator

        @Serializable
        data class StringComparison(
            override val propertyName: String,
            override val operator: ComparisonOperator,
            val value: String,
        ) : Comparison()

        @Serializable
        data class IntComparison(
            override val propertyName: String,
            override val operator: ComparisonOperator,
            val value: Int,
        ) : Comparison()

        @Serializable
        data class BooleanComparison(
            override val propertyName: String,
            override val operator: ComparisonOperator,
            val value: Boolean,
        ) : Comparison()
    }

    @Serializable
    data class Logical(
        val operator: LogicalOperator, val predicates: List<Predicate>
    ) : Predicate()
}
