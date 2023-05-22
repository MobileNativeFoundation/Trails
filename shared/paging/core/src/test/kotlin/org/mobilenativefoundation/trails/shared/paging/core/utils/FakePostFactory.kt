package org.mobilenativefoundation.trails.shared.paging.core.utils

class FakePostFactory {
    fun create(index: Int) = Post(
        id = "$index",
        title = "title_$index",
        authorName = "author_name_$index",
        content = "content_$index"
    )
}