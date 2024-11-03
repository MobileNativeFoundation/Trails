package org.mobilenativefoundation.trails.xplat.lib.operations.delete

import org.mobilenativefoundation.trails.xplat.lib.models.Model
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation

interface DeleteOneOperation<K : Model.Key> {
   suspend fun deleteOne(input: Operation.Mutation.Delete.DeleteOne<K>): Int
}