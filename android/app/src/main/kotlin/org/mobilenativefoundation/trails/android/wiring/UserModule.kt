package org.mobilenativefoundation.trails.android.wiring

import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import org.mobilenativefoundation.trails.android.RealTrailsUser
import org.mobilenativefoundation.trails.android.common.wiring.SingleIn
import org.mobilenativefoundation.trails.android.common.wiring.TrailsUser
import org.mobilenativefoundation.trails.android.common.wiring.UserScope
import org.mobilenativefoundation.trails.db.TrailsDb
import org.mobilenativefoundation.trails.shared.data.api.TrailsApi
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.data.entity.User
import org.mobilenativefoundation.trails.shared.paging.core.Pager
import org.mobilenativefoundation.trails.shared.timeline.TimelinePagerFactory

@Module
@ContributesTo(UserScope::class)
object UserModule {

    @Provides
    @SingleIn(UserScope::class)
    fun provideTrailsUser(user: User): TrailsUser = RealTrailsUser(user)

    @SingleIn(UserScope::class)
    @Provides
    fun provideTimelinePager(
        user: User,
        api: TrailsApi,
        trailsDb: TrailsDb
    ): Pager<Int, PostOverview, PostOverview> = TimelinePagerFactory(
        userId = user.id,
        api = api,
        paramsQueries = trailsDb.timelinePagingParamsQueries,
        pageQueries = trailsDb.timelinePagingDataQueries,
        postOverviewQueries = trailsDb.postOverviewQueries,
        timelinePostOverviewQueries = trailsDb.timelinePostOverviewQueries
    ).create()
}

