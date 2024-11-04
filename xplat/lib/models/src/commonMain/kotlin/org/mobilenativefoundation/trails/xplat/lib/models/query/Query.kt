package org.mobilenativefoundation.trails.xplat.lib.models.query

import kotlinx.serialization.Serializable

@Serializable
sealed class Query<out T: Any> {
    @Serializable
    class One<out T: Any>(
        val predicate: Predicate<T>?,
        val order: Order?,
    ) : Query<T>()

    @Serializable
    class Many<out T: Any>(
        val predicate: Predicate<T>?,
        val order: Order?,
        val limit: Int?
    ) : Query<T>()
}