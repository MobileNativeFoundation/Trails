package org.mobilenativefoundation.trails.common.networking.impl.client

import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.networking.api.client.Activities
import org.mobilenativefoundation.trails.common.networking.api.client.Bookmarks
import org.mobilenativefoundation.trails.common.networking.api.client.Hikes
import org.mobilenativefoundation.trails.common.networking.api.client.Reviews
import org.mobilenativefoundation.trails.common.networking.api.client.Trails
import org.mobilenativefoundation.trails.common.networking.api.client.TrailsClient

@Inject
class RealTrailsClient(
    override val activities: Activities,
    override val bookmarks: Bookmarks,
    override val hikes: Hikes,
    override val reviews: Reviews,
    override val trails: Trails
) : TrailsClient