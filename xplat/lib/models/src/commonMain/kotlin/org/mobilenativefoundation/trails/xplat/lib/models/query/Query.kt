package org.mobilenativefoundation.trails.xplat.lib.models.query

import kotlinx.serialization.Serializable

@Serializable
sealed class Query {
    class One(
        val predicate: Predicate<*>?,
        val order: Order?,
    ) : Query()

    class Many(
        val predicate: Predicate<*>?,
        val order: Order?,
        val limit: Int?
    ) : Query()
}