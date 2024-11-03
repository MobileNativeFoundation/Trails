package org.mobilenativefoundation.trails.xplat.lib.operations.read

import kotlinx.coroutines.flow.Flow
import org.mobilenativefoundation.trails.xplat.lib.models.Model
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation

interface ObserveOneOperation<K : Model.Key, P : Model.Properties, E : Model.Edges, N : Model.Node<K, P, E>> {
    fun observeOne(input: Operation.Query.ObserveOne<K>): Flow<N?>
}