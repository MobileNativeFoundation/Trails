package org.mobilenativefoundation.trails.xplat.lib.operations.read

import org.mobilenativefoundation.trails.xplat.lib.models.Model
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation

interface FindAllOperation<K : Model.Key, P : Model.Properties, E : Model.Edges, N : Model.Node<K, P, E>> {
    suspend fun findAll(input: Operation.Query.FindAll): List<N>
}