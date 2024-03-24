package org.mobilenativefoundation.trails.android.app

import me.tatarka.inject.annotations.Component
import org.mobilenativefoundation.trails.common.bookmarksTab.api.BookmarksComponent
import org.mobilenativefoundation.trails.common.core.api.CoreComponent
import org.mobilenativefoundation.trails.common.hikeTab.api.HikeComponent
import org.mobilenativefoundation.trails.common.homeTab.api.HomeComponent
import org.mobilenativefoundation.trails.common.navigation.api.NavigationComponent
import org.mobilenativefoundation.trails.common.networking.api.di.NetworkingComponent
import org.mobilenativefoundation.trails.common.profileTab.api.ProfileComponent
import org.mobilenativefoundation.trails.common.searchTab.api.SearchComponent

interface TrailsComponent {
    val bookmarks: BookmarksComponent
    val core: CoreComponent
    val hike: HikeComponent
    val home: HomeComponent
    val navigation: NavigationComponent
    val networking: NetworkingComponent
    val profile: ProfileComponent
    val search: SearchComponent
}

@Component
abstract class RealTrailsComponent(
    @Component override val bookmarks: BookmarksComponent,
    @Component override val core: CoreComponent,
    @Component override val hike: HikeComponent,
    @Component override val home: HomeComponent,
    @Component override val navigation: NavigationComponent,
    @Component override val networking: NetworkingComponent,
    @Component override val profile: ProfileComponent,
    @Component override val search: SearchComponent
) : TrailsComponent {

    companion object
}