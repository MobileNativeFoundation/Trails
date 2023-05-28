package org.mobilenativefoundation.trails.shared.paging.core.util

class FakeDatabase {
    private val posts = mutableMapOf<Int, PostPagingData.Item>()
    private val pages = mutableMapOf<PostPagingParams, PostPagingData.Page>()

    fun addPost(id: Int, post: PostPagingData.Item) {
        posts[id] = post
    }

    fun addPage(id: PostPagingParams, page: PostPagingData.Page) {
        pages[id] = page
    }

    fun findPostById(id: Int) = posts[id]
    fun findOnePage(params: PostPagingParams) =
        pages.values.firstOrNull { data -> data.params == params }
}
