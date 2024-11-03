package org.mobilenativefoundation.trails.xplat.lib.operations.query

import org.mobilenativefoundation.trails.xplat.lib.models.Model

interface QueryOneOperation<K : Model.Key, P : Model.Properties, E : Model.Edges, N : Model.Node<K, P, E>> {
    suspend fun queryOne(builder: QueryOneBuilder<N>.() -> Unit): N?
}