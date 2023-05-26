package org.mobilenativefoundation.trails.android.wiring

import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import org.mobilenativefoundation.trails.android.RealTrailsUser
import org.mobilenativefoundation.trails.android.common.wiring.SingleIn
import org.mobilenativefoundation.trails.android.common.wiring.TrailsUser
import org.mobilenativefoundation.trails.android.common.wiring.UserScope
import org.mobilenativefoundation.trails.shared.data.api.TrailsApi
import org.mobilenativefoundation.trails.shared.data.db.PageQueries
import org.mobilenativefoundation.trails.shared.data.db.PostOverviewQueries
import org.mobilenativefoundation.trails.shared.data.db.PostQueries
import org.mobilenativefoundation.trails.shared.data.entity.User
import org.mobilenativefoundation.trails.shared.timeline.TimelineRepository
import org.mobilenativefoundation.trails.shared.timeline.TimelineRepositoryBuilder

@Module
@ContributesTo(UserScope::class)
object UserModule {

    @Provides
    @SingleIn(UserScope::class)
    fun provideTrailsUser(user: User): TrailsUser = RealTrailsUser(user)
    @Provides
    @SingleIn(UserScope::class)
    fun provideTimelineRepository(
        user: TrailsUser,
        api: TrailsApi,
        pageQueries: PageQueries,
        postQueries: PostQueries,
        postOverviewQueries: PostOverviewQueries
    ): TimelineRepository = TimelineRepositoryBuilder(
        userId = user.id,
        api = api,
        pageQueries = pageQueries,
        postOverviewQueries = postOverviewQueries,
        postQueries = postQueries
    ).build()
}

