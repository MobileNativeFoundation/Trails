package org.mobilenativefoundation.trails.common.networking.api.di

import org.mobilenativefoundation.trails.common.networking.api.client.TrailsClient

interface NetworkingComponent {
    val trailsClient: TrailsClient
}