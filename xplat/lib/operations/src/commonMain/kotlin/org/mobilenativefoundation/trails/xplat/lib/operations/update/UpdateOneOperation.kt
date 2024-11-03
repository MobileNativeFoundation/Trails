package org.mobilenativefoundation.trails.xplat.lib.operations.update

import org.mobilenativefoundation.trails.xplat.lib.models.Model
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation

interface UpdateOneOperation<K : Model.Key, P : Model.Properties, E : Model.Edges, N : Model.Node<K, P, E>> {
    suspend fun updateOne(input: Operation.Mutation.Update.UpdateOne<K, P, E, N>): Int
}