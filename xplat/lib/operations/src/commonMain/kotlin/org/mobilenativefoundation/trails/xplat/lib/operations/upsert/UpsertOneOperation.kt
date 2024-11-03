package org.mobilenativefoundation.trails.xplat.lib.operations.upsert

import org.mobilenativefoundation.trails.xplat.lib.models.Model
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation

interface UpsertOneOperation<K : Model.Key, P : Model.Properties> {
    suspend fun upsertOne(input: Operation.Mutation.Upsert.UpsertOne<P>): K?
}