package org.mobilenativefoundation.trails.common.networking.impl.client

import io.ktor.client.*
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.networking.api.client.Bookmarks
import org.mobilenativefoundation.trails.common.networking.api.models.CreateBookmarkArgs
import org.mobilenativefoundation.trails.common.networking.api.models.CreateBookmarkResponse
import org.mobilenativefoundation.trails.common.networking.api.models.DeleteBookmarkArgs
import org.mobilenativefoundation.trails.common.networking.api.models.DeleteBookmarkResponse
import org.mobilenativefoundation.trails.common.networking.api.models.GetBookmarksArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetBookmarksResponse

@Inject
class RealBookmarks(
    private val httpClient: HttpClient
) : Bookmarks {
    override suspend fun createBookmark(args: CreateBookmarkArgs): CreateBookmarkResponse {
        return httpClient.post(args, Endpoints.CREATE_BOOKMARK)
    }

    override suspend fun getBookmarks(args: GetBookmarksArgs): GetBookmarksResponse {
        return httpClient.post(args, Endpoints.GET_BOOKMARK)
    }

    override suspend fun deleteBookmark(args: DeleteBookmarkArgs): DeleteBookmarkResponse {
        return httpClient.post(args, Endpoints.DELETE_BOOKMARK)
    }
}