package org.mobilenativefoundation.trails.xplat.lib.models.query

import kotlinx.serialization.Serializable

@Serializable
sealed class Query {
    @Serializable
    class One(
        val predicate: Predicate?,
        val order: Order?,
    ) : Query()

    @Serializable
    class Many(
        val predicate: Predicate?,
        val order: Order?,
        val limit: Int?
    ) : Query()
}