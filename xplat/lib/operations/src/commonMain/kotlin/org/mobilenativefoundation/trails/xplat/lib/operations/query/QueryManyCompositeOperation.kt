package org.mobilenativefoundation.trails.xplat.lib.operations.query

import org.mobilenativefoundation.trails.xplat.lib.models.Model

interface QueryManyCompositeOperation<K : Model.Key, P : Model.Properties, E : Model.Edges, N : Model.Node<K, P, E>, C : Model.Composite<K, P, E>> {
    suspend fun queryManyComposite(builder: QueryManyBuilder<N>.() -> Unit): List<C>
}