package org.mobilenativefoundation.trails.shared.paging.core.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.mobilenativefoundation.store.store5.SourceOfTruth

class FakePostSourceOfTruth : SourceOfTruth<String, Post> {

    private val items = mutableMapOf<String, Post>()
    override suspend fun delete(key: String) {
        items.remove(key)
    }

    override suspend fun deleteAll() {
        items.clear()
    }

    override suspend fun write(key: String, value: Post) {
        items[key] = value
    }

    override fun reader(key: String): Flow<Post?> = flow {
        println("SOT")
        emit(items[key])
    }

}