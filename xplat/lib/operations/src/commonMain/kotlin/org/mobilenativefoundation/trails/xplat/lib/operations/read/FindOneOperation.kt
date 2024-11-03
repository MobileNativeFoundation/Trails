package org.mobilenativefoundation.trails.xplat.lib.operations.read

import org.mobilenativefoundation.trails.xplat.lib.models.Model
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation

interface FindOneOperation<K : Model.Key, P : Model.Properties, E : Model.Edges, N : Model.Node<K, P, E>> {
    suspend fun findOne(input: Operation.Query.FindOne<K>): N?
}