package org.mobilenativefoundation.trails.common.networking.impl.client

object Endpoints {
    private const val ROOT_API_URL = "https://api.trails.ramotar.xyz/v1"

    private const val ACTIVITIES = "activities"
    private const val BOOKMARKS = "bookmarks"
    private const val HIKES = "hikes"
    private const val REVIEWS = "reviews"
    private const val TRAILS = "trails"

    const val GET_ACTIVITY = "$ROOT_API_URL/$ACTIVITIES/get_activity"
    const val GET_ACTIVITIES = "$ROOT_API_URL/$ACTIVITIES/get_activities"
    const val UPDATE_ACTIVITY = "$ROOT_API_URL/$ACTIVITIES/update_activity"
    const val DELETE_ACTIVITY = "$ROOT_API_URL/$ACTIVITIES/delete_activity"

    const val CREATE_BOOKMARK = "$ROOT_API_URL/$BOOKMARKS/create_bookmark"
    const val GET_BOOKMARK = "$ROOT_API_URL/$BOOKMARKS/get_bookmark"
    const val GET_BOOKMARKS = "$ROOT_API_URL/$BOOKMARKS/get_bookmarks"
    const val UPDATE_BOOKMARK = "$ROOT_API_URL/$BOOKMARKS/update_bookmark"
    const val DELETE_BOOKMARK = "$ROOT_API_URL/$BOOKMARKS/delete_bookmark"

    const val CREATE_HIKE = "$ROOT_API_URL/$HIKES/create_hike"
    const val GET_HIKE = "$ROOT_API_URL/$HIKES/get_hike"
    const val GET_HIKES = "$ROOT_API_URL/$HIKES/get_hikes"
    const val UPDATE_HIKE = "$ROOT_API_URL/$HIKES/update_hike"
    const val DELETE_HIKE = "$ROOT_API_URL/$HIKES/delete_hike"

    const val CREATE_REVIEW = "$ROOT_API_URL/$REVIEWS/create_review"
    const val GET_REVIEW = "$ROOT_API_URL/$REVIEWS/get_review"
    const val GET_REVIEWS = "$ROOT_API_URL/$REVIEWS/get_reviews"
    const val UPDATE_REVIEW = "$ROOT_API_URL/$REVIEWS/update_review"
    const val DELETE_REVIEW = "$ROOT_API_URL/$REVIEWS/delete_review"

    const val GET_TRAIL = "$ROOT_API_URL/$TRAILS/get_trail"
    const val GET_TRAILS = "$ROOT_API_URL/$TRAILS/get_trails"
    const val UPDATE_TRAIL = "$ROOT_API_URL/$TRAILS/update_trail"
    const val DELETE_TRAIL = "$ROOT_API_URL/$TRAILS/delete_trail"
}