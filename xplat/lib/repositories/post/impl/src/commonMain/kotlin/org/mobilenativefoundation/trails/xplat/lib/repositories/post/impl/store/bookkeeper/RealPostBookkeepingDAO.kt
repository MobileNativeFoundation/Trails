package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.bookkeeper

import org.mobilenativefoundation.trails.xplat.lib.db.*

class RealPostBookkeepingDAO(
    private val trailsDatabase: TrailsDatabase
) : PostBookkeepingDAO {
    override fun getOneFailedUpdate(id: Int): GetOneFailedUpdate? {
        TODO("Not yet implemented")
    }

    override fun getAllFailedUpdates(): List<GetFailedUpdates> {
        return trailsDatabase.postBookkeepingQueries.getFailedUpdates().executeAsList()
    }

    override fun getManyFailedUpdates(ids: List<Int>): List<GetManyFailedUpdates> {
        TODO("Not yet implemented")
    }

    override fun insertFailedUpdate(id: Int, timestamp: Long) {
        TODO("Not yet implemented")
    }

    override fun clearFailedUpdate(id: Int) {
        TODO("Not yet implemented")
    }

    override fun clearAllFailedUpdates() {
        TODO("Not yet implemented")
    }

    override fun getOneFailedDelete(id: Int): GetOneFailedDelete? {
        TODO("Not yet implemented")
    }

    override fun getAllFailedDeletes(): List<GetFailedDeletes> {
        TODO("Not yet implemented")
    }

    override fun getManyFailedDeletes(ids: List<Int>): List<GetManyFailedDeletes> {
        TODO("Not yet implemented")
    }

    override fun insertFailedDelete(id: Int, timestamp: Long) {
        TODO("Not yet implemented")
    }

    override fun clearFailedDelete(id: Int) {
        TODO("Not yet implemented")
    }

    override fun clearAllFailedDeletes() {
        TODO("Not yet implemented")
    }

}