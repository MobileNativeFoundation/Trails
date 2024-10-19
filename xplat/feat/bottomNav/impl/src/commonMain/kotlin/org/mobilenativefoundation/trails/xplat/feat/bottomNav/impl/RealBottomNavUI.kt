package org.mobilenativefoundation.trails.xplat.feat.bottomNav.impl

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.slack.circuit.runtime.screen.Screen
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.core.circuit.api.ScreenFactory
import org.mobilenativefoundation.trails.xplat.feat.bottomNav.api.BottomNavUI
import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.CarveIcon
import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.IconStyle
import org.mobilenativefoundation.trails.xplat.lib.carve.material3.Carve

@Inject
class RealBottomNavUI(
    private val screenFactory: ScreenFactory
) : BottomNavUI {
    @Composable
    override fun Content(selectedIndex: Int, onSelectedIndex: (index: Int, screen: Screen) -> Unit) {
        val items = remember(Unit) {
            listOf(
                BottomNavItem.HOME
            )
        }

        NavigationBar(
            containerColor = Carve.ColorScheme.surface
        ) {
            items.forEachIndexed { index, bottomNavItem ->
                val isSelected = selectedIndex == index

                NavigationBarItem(
                    icon = {
                        CarveIcon(
                            icon = bottomNavItem.icon,
                            style = if (isSelected) IconStyle.BOLD else IconStyle.LIGHT_OUTLINE
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        val screen: Screen = when (bottomNavItem) {
                            BottomNavItem.HOME -> screenFactory.homeScreen()
                        }

                        onSelectedIndex(index, screen)
                    }
                )
            }

        }
    }
}