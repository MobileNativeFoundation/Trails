package org.mobilenativefoundation.trails.xplat.lib.rest.api

import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.ResortOperations
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.TrailOperations
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.UserOperations

interface TrailsClient :
    ResortOperations,
    TrailOperations,
    UserOperations
