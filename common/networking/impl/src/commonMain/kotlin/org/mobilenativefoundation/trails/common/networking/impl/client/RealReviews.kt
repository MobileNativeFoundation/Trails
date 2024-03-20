package org.mobilenativefoundation.trails.common.networking.impl.client

import io.ktor.client.*
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.networking.api.client.Reviews
import org.mobilenativefoundation.trails.common.networking.api.models.CreateReviewArgs
import org.mobilenativefoundation.trails.common.networking.api.models.CreateReviewResponse
import org.mobilenativefoundation.trails.common.networking.api.models.DeleteReviewArgs
import org.mobilenativefoundation.trails.common.networking.api.models.DeleteReviewResponse
import org.mobilenativefoundation.trails.common.networking.api.models.GetReviewArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetReviewResponse
import org.mobilenativefoundation.trails.common.networking.api.models.GetReviewsArgs
import org.mobilenativefoundation.trails.common.networking.api.models.GetReviewsResponse
import org.mobilenativefoundation.trails.common.networking.api.models.UpdateReviewArgs
import org.mobilenativefoundation.trails.common.networking.api.models.UpdateReviewResponse

@Inject
class RealReviews(
    private val httpClient: HttpClient
) : Reviews {
    override suspend fun createReview(args: CreateReviewArgs): CreateReviewResponse {
        return httpClient.post(args, Endpoints.CREATE_REVIEW)
    }

    override suspend fun getReviews(args: GetReviewsArgs): GetReviewsResponse {
        return httpClient.post(args, Endpoints.GET_REVIEWS)
    }

    override suspend fun getReview(args: GetReviewArgs): GetReviewResponse {
        return httpClient.post(args, Endpoints.GET_REVIEW)
    }

    override suspend fun updateReview(args: UpdateReviewArgs): UpdateReviewResponse {
        return httpClient.post(args, Endpoints.UPDATE_REVIEW)
    }

    override suspend fun deleteReview(args: DeleteReviewArgs): DeleteReviewResponse {
        return httpClient.post(args, Endpoints.DELETE_REVIEW)
    }
}