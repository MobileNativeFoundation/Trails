package org.mobilenativefoundation.trails.common.networking.impl.di

import io.ktor.client.*
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.common.networking.api.client.Activities
import org.mobilenativefoundation.trails.common.networking.api.client.Bookmarks
import org.mobilenativefoundation.trails.common.networking.api.client.Hikes
import org.mobilenativefoundation.trails.common.networking.api.client.Reviews
import org.mobilenativefoundation.trails.common.networking.api.client.Trails
import org.mobilenativefoundation.trails.common.networking.api.client.TrailsClient
import org.mobilenativefoundation.trails.common.networking.api.di.NetworkingComponent
import org.mobilenativefoundation.trails.common.networking.impl.client.RealActivities
import org.mobilenativefoundation.trails.common.networking.impl.client.RealBookmarks
import org.mobilenativefoundation.trails.common.networking.impl.client.RealHikes
import org.mobilenativefoundation.trails.common.networking.impl.client.RealReviews
import org.mobilenativefoundation.trails.common.networking.impl.client.RealTrails
import org.mobilenativefoundation.trails.common.networking.impl.client.RealTrailsClient
import org.mobilenativefoundation.trails.common.networking.impl.client.httpClient

@Component
abstract class RealNetworkingComponent : NetworkingComponent {
    @Provides
    fun provideHttpClient(): HttpClient = httpClient()

    @Provides
    fun bindActivities(impl: RealActivities): Activities = impl

    @Provides
    fun bindBookmarks(impl: RealBookmarks): Bookmarks = impl

    @Provides
    fun bindHikes(impl: RealHikes): Hikes = impl

    @Provides
    fun bindReviews(impl: RealReviews): Reviews = impl

    @Provides
    fun bindTrails(impl: RealTrails): Trails = impl

    @Provides
    fun bindTrailsClient(impl: RealTrailsClient): TrailsClient = impl

    companion object
}