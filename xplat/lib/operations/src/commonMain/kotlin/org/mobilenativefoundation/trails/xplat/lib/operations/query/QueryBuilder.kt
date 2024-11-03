package org.mobilenativefoundation.trails.xplat.lib.operations.query

import org.mobilenativefoundation.trails.xplat.lib.models.query.Direction
import org.mobilenativefoundation.trails.xplat.lib.models.query.Order
import org.mobilenativefoundation.trails.xplat.lib.models.query.Predicate
import org.mobilenativefoundation.trails.xplat.lib.models.query.PredicateBuilder
import kotlin.reflect.KProperty1

class QueryOneBuilder<T : Any> {
    private var dataSources: DataSources = DataSources.all
    private var predicate: Predicate<T>? = null
    private var order: Order<T>? = null

    fun from(dataSources: DataSources) {
        this.dataSources = dataSources
    }

    fun where(block: PredicateBuilder<T>.() -> Predicate<T>) {
        predicate = PredicateBuilder<T>().block()
    }

    fun orderBy(property: KProperty1<T, Comparable<*>>, direction: Direction = Direction.ASC) {
        order = Order(property, direction)
    }


    fun build(): Query.One<T> {
        return Query.One(dataSources, predicate, order)
    }
}

class QueryManyBuilder<T : Any> {
    private var dataSources: DataSources = DataSources.all
    private var predicate: Predicate<T>? = null
    private var order: Order<T>? = null
    private var limit: Int = 1

    fun from(dataSources: DataSources) {
        this.dataSources = dataSources
    }

    fun where(block: PredicateBuilder<T>.() -> Predicate<T>) {
        predicate = PredicateBuilder<T>().block()
    }

    fun orderBy(property: KProperty1<T, Comparable<*>>, direction: Direction = Direction.ASC) {
        order = Order(property, direction)
    }

    fun limit(value: Int) {
        limit = value
    }

    fun build(): Query.Many<T> {
        return Query.Many(dataSources, predicate, order, limit)
    }
}

