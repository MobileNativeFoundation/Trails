package org.mobilenativefoundation.trails.shared.paging.core.util

class FakePostBackendService {
    fun getTimeline(
        params: PostPagingParams
    ): PostPagingData.Page {
        val start = params.offset?.plus(1) ?: 1
        val end = start + params.loadSize
        val next = params.copy(offset = end)

        return PostPagingData.Page(
            params = params,
            data = (start..end).map { id -> createPost(id) },
            next = next
        )
    }

    fun getPost(id: Int) = PostPagingData.Item(
        id = id,
        data = createPost(id)
    )

    private fun createPost(id: Int) = Post(id, "content_$id")
}
