package org.mobilenativefoundation.trails.common.networking.api.client

interface TrailsClient {
    val activities: Activities
    val bookmarks: Bookmarks
    val hikes: Hikes
    val reviews: Reviews
    val trails: Trails
}