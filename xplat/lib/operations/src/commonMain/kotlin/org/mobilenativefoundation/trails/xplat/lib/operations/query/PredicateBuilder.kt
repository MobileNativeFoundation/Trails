package org.mobilenativefoundation.trails.xplat.lib.operations.query

import org.mobilenativefoundation.trails.xplat.lib.models.query.ComparisonOperator
import org.mobilenativefoundation.trails.xplat.lib.models.query.LogicalOperator
import kotlin.reflect.KProperty1

class PredicateBuilder<T : Any> {
    infix fun <V : Any> KProperty1<T, V>.eq(value: V): Predicate<T, V> =
        Predicate.Comparison(this, ComparisonOperator.EQUALS, value)

    infix fun <V : Any> KProperty1<T, V>.contains(value: V): Predicate<T, V> =
        Predicate.Comparison(this, ComparisonOperator.CONTAINS, value)

    infix fun <V : Any> KProperty1<T, V>.notEq(value: V): Predicate<T, V> =
        Predicate.Comparison(this, ComparisonOperator.NOT_EQUALS, value)

    infix fun <V : Any> KProperty1<T, V>.greaterThan(value: V): Predicate<T, V> =
        Predicate.Comparison(this, ComparisonOperator.GREATER_THAN, value)

    infix fun <V : Any> KProperty1<T, V>.lessThan(value: V): Predicate<T, V> =
        Predicate.Comparison(this, ComparisonOperator.LESS_THAN, value)

    infix fun <V : Any> KProperty1<T, V>.greaterThanOrEq(value: V): Predicate<T, V> =
        Predicate.Comparison(this, ComparisonOperator.GREATER_THAN_OR_EQUALS, value)

    infix fun <V : Any> KProperty1<T, V>.lessThanOrEq(value: V): Predicate<T, V> =
        Predicate.Comparison(this, ComparisonOperator.LESS_THAN_OR_EQUALS, value)

    infix fun Predicate<T, *>.and(other: Predicate<T, *>): Predicate<T, *> =
        Predicate.Logical(LogicalOperator.AND, listOf(this, other))

    infix fun Predicate<T, *>.or(other: Predicate<T, *>): Predicate<T, *> =
        Predicate.Logical(LogicalOperator.OR, listOf(this, other))
}