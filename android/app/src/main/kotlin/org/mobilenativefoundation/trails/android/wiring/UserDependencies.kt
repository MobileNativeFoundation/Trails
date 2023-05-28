package org.mobilenativefoundation.trails.android.wiring

import com.squareup.anvil.annotations.ContributesTo
import org.mobilenativefoundation.trails.android.common.wiring.UserScope
import org.mobilenativefoundation.trails.shared.data.entity.PostOverview
import org.mobilenativefoundation.trails.shared.paging.core.Pager

@ContributesTo(UserScope::class)
interface UserDependencies {
    val timelinePager: Pager<Int, PostOverview, PostOverview>
}