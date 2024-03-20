package org.mobilenativefoundation.trails.common.networking.api.client

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

interface Reviews {
    suspend fun createReview(args: CreateReviewArgs): CreateReviewResponse
    suspend fun getReviews(args: GetReviewsArgs): GetReviewsResponse
    suspend fun getReview(args: GetReviewArgs): GetReviewResponse
    suspend fun updateReview(args: UpdateReviewArgs): UpdateReviewResponse
    suspend fun deleteReview(args: DeleteReviewArgs): DeleteReviewResponse
}