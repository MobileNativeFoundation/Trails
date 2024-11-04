package org.mobilenativefoundation.trails.xplat.lib.models.query

import kotlin.reflect.KProperty1

class PredicateBuilder<T : Any> {
    infix fun <V : Any> KProperty1<T, V>.eq(value: V): Predicate<V> =
        Predicate.Comparison(this.name, ComparisonOperator.EQUALS, value, valueTypeOf(value))

    infix fun <V : Any> KProperty1<T, V>.contains(value: V): Predicate<V> =
        Predicate.Comparison(this.name, ComparisonOperator.CONTAINS, value, valueTypeOf(value))

    infix fun <V : Any> KProperty1<T, V>.notEq(value: V): Predicate<V> =
        Predicate.Comparison(this.name, ComparisonOperator.NOT_EQUALS, value, valueTypeOf(value))

    infix fun <V : Any> KProperty1<T, V>.greaterThan(value: V): Predicate<V> =
        Predicate.Comparison(this.name, ComparisonOperator.GREATER_THAN, value, valueTypeOf(value))

    infix fun <V : Any> KProperty1<T, V>.lessThan(value: V): Predicate<V> =
        Predicate.Comparison(this.name, ComparisonOperator.LESS_THAN, value, valueTypeOf(value))

    infix fun <V : Any> KProperty1<T, V>.greaterThanOrEq(value: V): Predicate<V> =
        Predicate.Comparison(this.name, ComparisonOperator.GREATER_THAN_OR_EQUALS, value, valueTypeOf(value))

    infix fun <V : Any> KProperty1<T, V>.lessThanOrEq(value: V): Predicate<V> =
        Predicate.Comparison(this.name, ComparisonOperator.LESS_THAN_OR_EQUALS, value, valueTypeOf(value))

    infix fun Predicate<T>.and(other: Predicate<T>): Predicate<T> =
        Predicate.Logical(LogicalOperator.AND, listOf(this, other))

    infix fun Predicate<T>.or(other: Predicate<T>): Predicate<T> =
        Predicate.Logical(LogicalOperator.OR, listOf(this, other))
}

fun <V : Any> valueTypeOf(value: V): Type = when (value) {
    is String -> Type.STRING
    is Boolean -> Type.BOOLEAN
    is Int -> Type.INT
    is Long -> Type.LONG
    else -> error("Unsupported value type.")
}