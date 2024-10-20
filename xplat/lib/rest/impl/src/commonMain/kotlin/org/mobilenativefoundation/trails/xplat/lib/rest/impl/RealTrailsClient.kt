package org.mobilenativefoundation.trails.xplat.lib.rest.impl

import org.mobilenativefoundation.trails.xplat.lib.rest.api.TrailsClient
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.PostOperations

class RealTrailsClient(
    private val postOperations: PostOperations
) : TrailsClient,
    PostOperations by postOperations