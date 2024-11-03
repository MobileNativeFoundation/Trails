package org.mobilenativefoundation.trails.xplat.lib.rest.impl

import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.lib.rest.api.TrailsClient
import org.mobilenativefoundation.trails.xplat.lib.rest.api.post.PostOperations

@Inject
class RealTrailsClient(
    private val postOperations: PostOperations
) : TrailsClient,
    PostOperations by postOperations