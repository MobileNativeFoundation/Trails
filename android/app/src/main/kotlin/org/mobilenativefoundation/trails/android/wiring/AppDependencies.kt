package org.mobilenativefoundation.trails.android.wiring


import com.squareup.anvil.annotations.ContributesTo
import org.mobilenativefoundation.trails.android.common.wiring.AppScope
import org.mobilenativefoundation.trails.shared.data.entity.Post
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.paging.core.PagingRepository

@ContributesTo(AppScope::class)
interface AppDependencies