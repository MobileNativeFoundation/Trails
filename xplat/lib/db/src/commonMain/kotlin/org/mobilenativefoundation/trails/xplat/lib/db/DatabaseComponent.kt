package org.mobilenativefoundation.trails.xplat.lib.db

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

interface DatabaseComponent {
    val trailsDatabaseFactory: TrailsDatabaseFactory
}

@Component
abstract class RealDatabaseComponent: DatabaseComponent {
    @Provides
    fun bindTrailsDatabaseFactory(impl: RealTrailsDatabaseFactory): TrailsDatabaseFactory = impl
    companion object
}