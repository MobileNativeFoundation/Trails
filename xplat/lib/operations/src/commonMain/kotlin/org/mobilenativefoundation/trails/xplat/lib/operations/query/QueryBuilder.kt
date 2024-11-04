package org.mobilenativefoundation.trails.xplat.lib.operations.query

import org.mobilenativefoundation.trails.xplat.lib.models.query.Direction
import org.mobilenativefoundation.trails.xplat.lib.models.query.PropertyValueType
import kotlin.reflect.KProperty1

class QueryOneBuilder<T : Any> {
    private var dataSources: DataSources = DataSources.all
    private var predicate: Predicate<T, *>? = null
    private var order: Order<T>? = null

    fun from(dataSources: DataSources) {
        this.dataSources = dataSources
    }

    fun where(block: PredicateBuilder<T>.() -> Predicate<T, *>) {
        predicate = PredicateBuilder<T>().block()
    }

    fun orderBy(block: OrderByBuilder<T>.() -> Unit) {
        val builder = OrderByBuilder<T>().apply(block)
        order = builder.build()
    }


    fun build(): Query.One<T> {
        return Query.One(dataSources, predicate, order)
    }
}


class OrderByBuilder<T : Any> {
    private lateinit var property: KProperty1<T, *>
    private var direction: Direction = Direction.ASC
    private lateinit var propertyValueType: PropertyValueType

    infix fun direction(direction: Direction) {
        this.direction = direction
    }

    infix fun type(propertyValueType: PropertyValueType) {
        this.propertyValueType = propertyValueType
    }

    infix fun property(property: KProperty1<T, *>) {
        this.property = property
    }

    internal fun build(): Order<T> = Order(property, direction, propertyValueType)
}

class QueryManyBuilder<T : Any> {
    private var dataSources: DataSources = DataSources.all
    private var predicate: Predicate<T, *>? = null
    var order: Order<T>? = null
    private var limit: Int = 1

    fun from(dataSources: DataSources) {
        this.dataSources = dataSources
    }

    fun where(block: PredicateBuilder<T>.() -> Predicate<T, *>) {
        predicate = PredicateBuilder<T>().block()
    }

    fun orderBy(order: Order<T>) {
        this.order = order
    }


    inline fun <reified V : Any> orderBy(property: KProperty1<T, V>, direction: Direction = Direction.ASC) {
        val propertyValueType = when (V::class) {
            String::class -> PropertyValueType.STRING
            Int::class -> PropertyValueType.INT
            Long::class -> PropertyValueType.LONG
            else -> error("Unsupported type: ${V::class}.")
        }

        this.order = Order(property, direction, propertyValueType = propertyValueType)
    }

    fun limit(value: Int) {
        limit = value
    }

    fun build(): Query.Many<T> {
        return Query.Many(dataSources, predicate, order, limit)
    }
}

