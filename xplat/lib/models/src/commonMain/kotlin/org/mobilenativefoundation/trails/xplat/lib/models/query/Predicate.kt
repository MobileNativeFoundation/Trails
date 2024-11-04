package org.mobilenativefoundation.trails.xplat.lib.models.query

import kotlinx.serialization.Serializable

@Serializable

sealed class Predicate<out T : Any> {
    @Serializable
    data class Comparison<T : Any>(
        val propertyName: String,
        val operator: ComparisonOperator,
        val value: T,
        val valuePropertyValueType: PropertyValueType
    ) : Predicate<T>()

    @Serializable
    data class Logical<T : Any>(
        val operator: LogicalOperator,
        val predicates: List<Predicate<T>>
    ) : Predicate<T>()
}

enum class PropertyValueType {
    STRING, BOOLEAN, INT, LONG
}

