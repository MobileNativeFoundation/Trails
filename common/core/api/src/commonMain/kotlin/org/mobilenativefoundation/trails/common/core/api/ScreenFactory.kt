package org.mobilenativefoundation.trails.common.core.api

import org.mobilenativefoundation.trails.common.bookmarksTab.api.BookmarksScreen
import org.mobilenativefoundation.trails.common.hikeTab.api.HikeScreen
import org.mobilenativefoundation.trails.common.homeTab.api.HomeScreen
import org.mobilenativefoundation.trails.common.profileTab.api.ProfileScreen
import org.mobilenativefoundation.trails.common.searchTab.api.SearchScreen


interface ScreenFactory {
    fun homeScreen(): HomeScreen
    fun bookmarksScreen(): BookmarksScreen
    fun searchScreen(): SearchScreen
    fun profileScreen(): ProfileScreen
    fun hikeScreen(): HikeScreen
}

