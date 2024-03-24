package org.mobilenativefoundation.trails.common.navigation.impl

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.slack.circuit.runtime.screen.Screen
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.core.api.ScreenFactory
import org.mobilenativefoundation.trails.common.navigation.api.BottomNavBar
import org.mobilenativefoundation.trails.common.tig.compose.theme.TIG

@Inject
class RealBottomNavBar(
    private val screenFactory: ScreenFactory
) : BottomNavBar {

    @Composable
    override fun Content(selectedIndex: Int, onSelectedIndex: (index: Int, screen: Screen) -> Unit) {
        val items = remember {
            listOf(
                BottomNavItem.Home,
                BottomNavItem.Search,
                BottomNavItem.Hike,
                BottomNavItem.Bookmarks,
                BottomNavItem.Profile
            )
        }

        NavigationBar(containerColor = TIG.ColorScheme.background) {
            items.forEachIndexed { index, item ->

                val isSelected = selectedIndex == index

                NavigationBarItem(
                    icon = {
                        val painter = if (isSelected) item.selectedIcon else item.unselectedIcon
                        Icon(painter, contentDescription = item.title)
                    },
                    label = { Text(text = item.title) },
                    alwaysShowLabel = false,
                    selected = selectedIndex == index,
                    onClick = {
                        val screen = when (item) {
                            BottomNavItem.Bookmarks -> screenFactory.bookmarksScreen()
                            BottomNavItem.Hike -> screenFactory.hikeScreen()
                            BottomNavItem.Home -> screenFactory.homeScreen()
                            BottomNavItem.Profile -> screenFactory.profileScreen()
                            BottomNavItem.Search -> screenFactory.searchScreen()
                        }

                        onSelectedIndex(index, screen)
                    }
                )
            }
        }
    }
}