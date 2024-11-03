package org.mobilenativefoundation.trails.xplat.lib.operations.delete

interface DeleteAllOperation {
    suspend fun deleteAll(): Int
}