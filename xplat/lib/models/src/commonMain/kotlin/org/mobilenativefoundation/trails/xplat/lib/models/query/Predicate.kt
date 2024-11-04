package org.mobilenativefoundation.trails.xplat.lib.models.query

import kotlin.reflect.KProperty1

sealed class Predicate<T> {
    data class Comparison<T, V : Any>(
        val property: KProperty1<T, V>,
        val operator: ComparisonOperator,
        val value: V
    ) : Predicate<T>()

    data class Logical<T>(
        val operator: LogicalOperator,
        val predicates: List<Predicate<T>>
    ) : Predicate<T>()
}