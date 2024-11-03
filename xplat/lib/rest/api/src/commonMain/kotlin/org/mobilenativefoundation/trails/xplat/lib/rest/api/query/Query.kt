package org.mobilenativefoundation.trails.xplat.lib.rest.api.query

import org.mobilenativefoundation.trails.xplat.lib.models.query.Order
import org.mobilenativefoundation.trails.xplat.lib.models.query.Predicate

sealed class Query<out T : Any> {
    class One<T : Any>(
        val predicate: Predicate<T>?,
        val order: Order<T>?,
    ) : Query<T>()

    class Many<T : Any>(
        val predicate: Predicate<T>?,
        val order: Order<T>?,
        val limit: Int?
    ) : Query<T>()
}