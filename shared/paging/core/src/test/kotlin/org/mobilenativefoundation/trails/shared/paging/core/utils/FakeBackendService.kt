package org.mobilenativefoundation.trails.shared.paging.core.utils

import org.mobilenativefoundation.trails.shared.paging.core.PagingData
import org.mobilenativefoundation.trails.shared.paging.core.PagingParams

class FakeBackendService {
    private val posts = mutableMapOf<String, Post>()
    private val timeline = mutableListOf<Post>()

    fun timeline(params: PagingParams<String>): PagingData.Page<String, PostOverview> {
        val start = if (params.after == null) {
            0
        } else {
            maxOf(timeline.indexOfFirst { it.id == params.after }, 0)
        }

        val end = start + params.limit

        val items = timeline.subList(start, end).map {
            PostOverview(id = it.id, title = it.title, authorName = it.authorName)
        }

        println("""
            start = $start
            end = $end
            items = $items
        """.trimIndent())

        val after = if (end <= timeline.lastIndex) {
            timeline[end].id
        } else {
            null
        }
        val next = PagingParams(params.limit, after)

        return PagingData.Page(
            params = params,
            items = items,
            next = next
        )
    }

    fun detail(id: String): Post? = posts[id]
    fun overview(id: String): PostOverview? = posts[id]?.let { PostOverview(it.id, it.title, it.authorName) }

    init {
        val factory = FakePostFactory()
        (timeline.addAll((1..100).map {
            val post = factory.create(it)
            posts[it.toString()] = post
            post
        }))
    }
}