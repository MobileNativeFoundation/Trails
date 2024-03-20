package org.mobilenativefoundation.trails.common.networking.api.client

import org.mobilenativefoundation.trails.common.networking.api.models.CreateBookmarkArgs
import org.mobilenativefoundation.trails.common.networking.api.models.CreateBookmarkResponse
import org.mobilenativefoundation.trails.common.networking.api.models.DeleteBookmarkArgs
import org.mobilenativefoundation.trails.common.networking.api.models.DeleteBookmarkResponse
import org.mobilenativefoundation.trails.common.networking.api.models.GetBookmarksArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetBookmarksResponse

interface Bookmarks {
    suspend fun createBookmark(args: CreateBookmarkArgs): CreateBookmarkResponse
    suspend fun getBookmarks(args: GetBookmarksArgs): GetBookmarksResponse
    suspend fun deleteBookmark(args: DeleteBookmarkArgs): DeleteBookmarkResponse
}