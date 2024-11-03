package org.mobilenativefoundation.trails.xplat.lib.rest.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.xplat.lib.rest.api.TrailsClient
import org.mobilenativefoundation.trails.xplat.lib.rest.api.TrailsClientComponent
import org.mobilenativefoundation.trails.xplat.lib.rest.api.post.PostOperations
import org.mobilenativefoundation.trails.xplat.lib.rest.impl.operations.RealPostOperations

@Component
abstract class RealTrailsClientComponent : TrailsClientComponent {
    @Provides
    fun bindPostOperations(impl: RealPostOperations): PostOperations = impl

    @Provides
    fun bindTrailsClient(impl: RealTrailsClient): TrailsClient = impl

    companion object
}