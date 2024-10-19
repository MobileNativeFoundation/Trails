package org.mobilenativefoundation.trails.xplat.lib.rest.api.operations

import org.mobilenativefoundation.trails.xplat.lib.rest.api.models.CreateResortArgs
import org.mobilenativefoundation.trails.xplat.lib.rest.api.models.Resort

interface ResortOperations {
    suspend fun getResort(id: Int): Resort
    suspend fun getAllResorts(): List<Resort>
    suspend fun createResort(args: CreateResortArgs): Resort
}