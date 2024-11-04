package org.mobilenativefoundation.trails.xplat.lib.operations.query

import org.mobilenativefoundation.trails.xplat.lib.models.query.ComparisonOperator
import org.mobilenativefoundation.trails.xplat.lib.models.query.LogicalOperator
import kotlin.reflect.KProperty1

sealed class Predicate<out T: Any, out V: Any> {
    data class Comparison<T: Any, V : Any>(
        val property: KProperty1<T, V>,
        val operator: ComparisonOperator,
        val value: V
    ) : Predicate<T, V>()

    data class Logical<T: Any, V: Any>(
        val operator: LogicalOperator,
        val predicates: List<Predicate<T, V>>
    ) : Predicate<T, V>()
}