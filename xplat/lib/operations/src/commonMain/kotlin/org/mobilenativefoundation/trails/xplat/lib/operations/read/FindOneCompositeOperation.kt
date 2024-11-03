package org.mobilenativefoundation.trails.xplat.lib.operations.read

import org.mobilenativefoundation.trails.xplat.lib.models.Model
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation

interface FindOneCompositeOperation<K : Model.Key, P : Model.Properties, E : Model.Edges, C : Model.Composite<K, P, E>> {
    suspend fun findOneComposite(input: Operation.Query.FindOneComposite<K>): C?
}