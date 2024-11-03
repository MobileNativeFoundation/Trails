package org.mobilenativefoundation.trails.xplat.lib.operations.delete

import org.mobilenativefoundation.trails.xplat.lib.models.Model
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation

interface DeleteManyOperation<K : Model.Key> {
    suspend fun deleteMany(input: Operation.Mutation.Delete.DeleteMany<K>): Int
}