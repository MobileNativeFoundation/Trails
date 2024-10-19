package org.mobilenativefoundation.trails.xplat.lib.rest.api.operations

import org.mobilenativefoundation.trails.xplat.lib.rest.api.models.CreateResortTagArgs
import org.mobilenativefoundation.trails.xplat.lib.rest.api.models.ResortTag

interface UserResortTagOperations {
    suspend fun createResortTag(args: CreateResortTagArgs): ResortTag
    suspend fun getUserResortTags(userId: Int): List<ResortTag>
}