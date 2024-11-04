package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.database

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.PostOperation
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput

class PostSourceOfTruthWriter(
    private val trailsDatabase: TrailsDatabase,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    private val coroutineScope = CoroutineScope(coroutineDispatcher)

    // We write to the SOT on mutations and queries
    // So we need to handle all cases

    fun handleWrite(
        operation: PostOperation,
        value: PostOutput
    ) {

    }
}