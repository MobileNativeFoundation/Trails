package org.mobilenativefoundation.trails.xplat.lib.operations.update

import org.mobilenativefoundation.trails.xplat.lib.models.Model
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation

interface ReplaceOneOperation<K : Model.Key, P : Model.Properties, E : Model.Edges, N : Model.Node<K, P, E>> {
    suspend fun replaceOne(input: Operation.Mutation.Update.ReplaceOne<K, P, E, N>): Int
}