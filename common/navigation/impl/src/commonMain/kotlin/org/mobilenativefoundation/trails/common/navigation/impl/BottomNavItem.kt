package org.mobilenativefoundation.trails.common.navigation.impl

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.slack.circuit.runtime.screen.Screen
import org.mobilenativefoundation.trails.common.bookmarksTab.api.BookmarksScreen
import org.mobilenativefoundation.trails.common.hikeTab.api.HikeScreen
import org.mobilenativefoundation.trails.common.homeTab.api.HomeScreen
import org.mobilenativefoundation.trails.common.profileTab.api.ProfileScreen
import org.mobilenativefoundation.trails.common.searchTab.api.SearchScreen
import org.mobilenativefoundation.trails.common.tig.compose.drawable.*


private const val HOME_TAB = "Home"
private const val SEARCH_TAB = "Search"
private const val PROFILE_TAB = "Profile"
private const val BOOKMARKS_TAB = "Bookmarks"
private const val HIKE_TAB = "Hike"


sealed class BottomNavItem(val title: String) {
    abstract val selectedIcon: Painter
        @Composable get

    abstract val unselectedIcon: Painter
        @Composable get

    abstract val screen: Screen


    data object Home : BottomNavItem(HOME_TAB) {
        override val selectedIcon: Painter
            @Composable get() = homeIcon

        override val unselectedIcon: Painter
            @Composable get() = homeIcon

        override val screen: Screen = HomeScreen
    }

    data object Search : BottomNavItem(SEARCH_TAB) {
        override val selectedIcon: Painter
            @Composable get() = searchIcon

        override val unselectedIcon: Painter
            @Composable get() = searchIcon

        override val screen: Screen = SearchScreen
    }

    data object Profile : BottomNavItem(PROFILE_TAB) {
        override val selectedIcon: Painter
            @Composable get() = profileIcon

        override val unselectedIcon: Painter
            @Composable get() = profileIcon

        override val screen: Screen = ProfileScreen
    }

    data object Bookmarks : BottomNavItem(BOOKMARKS_TAB) {
        override val selectedIcon: Painter
            @Composable get() = heartIcon

        override val unselectedIcon: Painter
            @Composable get() = heartIcon

        override val screen: Screen = BookmarksScreen
    }

    data object Hike : BottomNavItem(HIKE_TAB) {
        override val selectedIcon: Painter
            @Composable get() = activityIcon

        override val unselectedIcon: Painter
            @Composable get() = activityIcon

        override val screen: Screen = HikeScreen
    }
}