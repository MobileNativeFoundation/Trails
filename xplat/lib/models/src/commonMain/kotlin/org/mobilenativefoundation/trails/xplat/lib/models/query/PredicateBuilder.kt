package org.mobilenativefoundation.trails.xplat.lib.models.query

import kotlin.reflect.KProperty1

class PredicateBuilder<T : Any> {
    inline infix fun <reified V : Any> KProperty1<T, V>.eq(value: V): Predicate {
        val propertyName = this.name
        val operator = ComparisonOperator.EQUALS
        return when (V::class) {
            String::class -> Predicate.Comparison.StringComparison(propertyName, operator, value as String)
            Int::class -> Predicate.Comparison.IntComparison(propertyName, operator, value as Int)
            Boolean::class -> Predicate.Comparison.BooleanComparison(propertyName, operator, value as Boolean)
            else -> throw UnsupportedOperationException("Unsupported type ${V::class}.")
        }
    }


    inline infix fun <reified V : Any> KProperty1<T, V>.contains(value: V): Predicate {
        val propertyName = this.name
        val operator = ComparisonOperator.CONTAINS
        return when (V::class) {
            String::class -> Predicate.Comparison.StringComparison(propertyName, operator, value as String)
            else -> throw UnsupportedOperationException("Unsupported type ${V::class}.")
        }
    }

    inline infix fun <reified V : Any> KProperty1<T, V>.notEq(value: V): Predicate {
        val propertyName = this.name
        val operator = ComparisonOperator.NOT_EQUALS
        return when (V::class) {
            String::class -> Predicate.Comparison.StringComparison(propertyName, operator, value as String)
            Int::class -> Predicate.Comparison.IntComparison(propertyName, operator, value as Int)
            Boolean::class -> Predicate.Comparison.BooleanComparison(propertyName, operator, value as Boolean)
            else -> throw UnsupportedOperationException("Unsupported type ${V::class}.")
        }
    }

    inline infix fun <reified V : Any> KProperty1<T, V>.greaterThan(value: V): Predicate {
        val propertyName = this.name
        val operator = ComparisonOperator.GREATER_THAN
        return when (V::class) {
            String::class -> Predicate.Comparison.StringComparison(propertyName, operator, value as String)
            Int::class -> Predicate.Comparison.IntComparison(propertyName, operator, value as Int)
            Boolean::class -> Predicate.Comparison.BooleanComparison(propertyName, operator, value as Boolean)
            else -> throw UnsupportedOperationException("Unsupported type ${V::class}.")
        }
    }

    inline infix fun <reified V : Any> KProperty1<T, V>.lessThan(value: V): Predicate {
        val propertyName = this.name
        val operator = ComparisonOperator.LESS_THAN
        return when (V::class) {
            String::class -> Predicate.Comparison.StringComparison(propertyName, operator, value as String)
            Int::class -> Predicate.Comparison.IntComparison(propertyName, operator, value as Int)
            Boolean::class -> Predicate.Comparison.BooleanComparison(propertyName, operator, value as Boolean)
            else -> throw UnsupportedOperationException("Unsupported type ${V::class}.")
        }
    }

    inline infix fun <reified V : Any> KProperty1<T, V>.greaterThanOrEq(value: V): Predicate {
        val propertyName = this.name
        val operator = ComparisonOperator.GREATER_THAN_OR_EQUALS
        return when (V::class) {
            String::class -> Predicate.Comparison.StringComparison(propertyName, operator, value as String)
            Int::class -> Predicate.Comparison.IntComparison(propertyName, operator, value as Int)
            Boolean::class -> Predicate.Comparison.BooleanComparison(propertyName, operator, value as Boolean)
            else -> throw UnsupportedOperationException("Unsupported type ${V::class}.")
        }
    }

    inline infix fun <reified V : Any> KProperty1<T, V>.lessThanOrEq(value: V): Predicate {
        val propertyName = this.name
        val operator = ComparisonOperator.LESS_THAN_OR_EQUALS
        return when (V::class) {
            String::class -> Predicate.Comparison.StringComparison(propertyName, operator, value as String)
            Int::class -> Predicate.Comparison.IntComparison(propertyName, operator, value as Int)
            Boolean::class -> Predicate.Comparison.BooleanComparison(propertyName, operator, value as Boolean)
            else -> throw UnsupportedOperationException("Unsupported type ${V::class}.")
        }
    }

    infix fun Predicate.and(other: Predicate): Predicate =
        Predicate.Logical(LogicalOperator.AND, listOf(this, other))

    infix fun Predicate.or(other: Predicate): Predicate =
        Predicate.Logical(LogicalOperator.OR, listOf(this, other))
}

