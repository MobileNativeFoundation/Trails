package org.mobilenativefoundation.trails.xplat.lib.rest.api.operations

import org.mobilenativefoundation.trails.xplat.lib.rest.api.models.User

interface UserOperations : UserResortTagOperations {
    suspend fun getUser(userId: Int): User
}

