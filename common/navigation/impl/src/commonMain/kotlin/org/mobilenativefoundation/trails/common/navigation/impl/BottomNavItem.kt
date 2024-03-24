package org.mobilenativefoundation.trails.common.navigation.impl

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
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


    data object Home : BottomNavItem(HOME_TAB) {
        override val selectedIcon: Painter
            @Composable get() = homeIcon

        override val unselectedIcon: Painter
            @Composable get() = homeIcon
    }

    data object Search : BottomNavItem(SEARCH_TAB) {
        override val selectedIcon: Painter
            @Composable get() = searchIcon

        override val unselectedIcon: Painter
            @Composable get() = searchIcon
    }

    data object Profile : BottomNavItem(PROFILE_TAB) {
        override val selectedIcon: Painter
            @Composable get() = profileIcon

        override val unselectedIcon: Painter
            @Composable get() = profileIcon
    }

    data object Bookmarks : BottomNavItem(BOOKMARKS_TAB) {
        override val selectedIcon: Painter
            @Composable get() = heartIcon

        override val unselectedIcon: Painter
            @Composable get() = heartIcon
    }

    data object Hike : BottomNavItem(HIKE_TAB) {
        override val selectedIcon: Painter
            @Composable get() = activityIcon

        override val unselectedIcon: Painter
            @Composable get() = activityIcon
    }
}