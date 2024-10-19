package org.mobilenativefoundation.trails.xplat.lib.rest.impl

import org.mobilenativefoundation.trails.xplat.lib.rest.api.TrailsClient
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.ResortOperations
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.TrailOperations
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.UserOperations

class RealTrailsClient(
    private val resortOperations: ResortOperations,
    private val trailOperations: TrailOperations,
    private val userOperations: UserOperations
) : TrailsClient,
    ResortOperations by resortOperations,
    TrailOperations by trailOperations,
    UserOperations by userOperations