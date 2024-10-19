package org.mobilenativefoundation.trails.xplat.lib.rest.api.operations

import org.mobilenativefoundation.trails.xplat.lib.rest.api.models.Trail

interface TrailOperations {
    suspend fun getTrail(id: Int): Trail
    suspend fun getTrailsByResort(resortId: Int): List<Trail>
    suspend fun createTrail(trail: Trail): Trail
    suspend fun updateTrail(id: Int, trail: Trail): Trail
    suspend fun deleteTrail(id: Int)
}