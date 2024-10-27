package org.mobilenativefoundation.trails.xplat.lib.rest.api.operations

import org.mobilenativefoundation.trails.backend.models.CompositePost

interface PostOperations {
    suspend fun getPosts(): List<CompositePost>
}