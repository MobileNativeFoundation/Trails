package org.mobilenativefoundation.trails.xplat.lib.models.query

import kotlinx.serialization.Serializable

@Serializable
sealed class Predicate<out T : Any> {
    @Serializable
    data class Comparison<T : Any>(
        val propertyName: String,
        val operator: ComparisonOperator,
        val value: T,
        val type: Type
    ) : Predicate<T>()

    @Serializable
    data class Logical<T : Any>(
        val operator: LogicalOperator,
        val predicates: List<Predicate<T>>
    ) : Predicate<T>()
}

enum class Type {
    STRING, BOOLEAN, INT, LONG
}