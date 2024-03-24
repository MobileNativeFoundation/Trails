package org.mobilenativefoundation.trails.common.core.impl

import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.bookmarksTab.api.BookmarksScreen
import org.mobilenativefoundation.trails.common.core.api.ScreenFactory
import org.mobilenativefoundation.trails.common.hikeTab.api.HikeScreen
import org.mobilenativefoundation.trails.common.homeTab.api.HomeScreen
import org.mobilenativefoundation.trails.common.profileTab.api.ProfileScreen
import org.mobilenativefoundation.trails.common.searchTab.api.SearchScreen

@Inject
class RealScreenFactory(
    private val homeScreen: HomeScreen,
    private val bookmarksScreen: BookmarksScreen,
    private val searchScreen: SearchScreen,
    private val profileScreen: ProfileScreen,
    private val hikeScreen: HikeScreen
) : ScreenFactory {
    override fun homeScreen(): HomeScreen = homeScreen

    override fun bookmarksScreen(): BookmarksScreen = bookmarksScreen

    override fun searchScreen(): SearchScreen = searchScreen

    override fun profileScreen(): ProfileScreen = profileScreen

    override fun hikeScreen(): HikeScreen = hikeScreen
}