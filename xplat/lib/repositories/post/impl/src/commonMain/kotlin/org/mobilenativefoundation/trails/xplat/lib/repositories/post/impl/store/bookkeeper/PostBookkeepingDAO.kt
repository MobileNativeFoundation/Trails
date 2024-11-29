package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper

import org.mobilenativefoundation.trails.xplat.lib.db.*

interface PostBookkeepingDAO {
    fun getOneFailedUpdate(id: Int): GetOneFailedUpdate?
    fun getAllFailedUpdates(): List<GetFailedUpdates>
    fun getManyFailedUpdates(ids: List<Int>): List<GetManyFailedUpdates>
    fun insertFailedUpdate(id: Int, timestamp: Long)
    fun clearFailedUpdate(id: Int)
    fun clearAllFailedUpdates()

    fun getOneFailedDelete(id: Int): GetOneFailedDelete?
    fun getAllFailedDeletes(): List<GetFailedDeletes>
    fun getManyFailedDeletes(ids: List<Int>): List<GetManyFailedDeletes>
    fun insertFailedDelete(id: Int, timestamp: Long)
    fun clearFailedDelete(id: Int)
    fun clearAllFailedDeletes()
}