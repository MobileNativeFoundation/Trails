package org.mobilenativefoundation.trails.xplat.feat.bottomNav.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.slack.circuit.runtime.screen.Screen
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.core.circuit.api.ScreenFactory
import org.mobilenativefoundation.trails.xplat.feat.bottomNav.api.BottomNavUI
import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.CarveIcon
import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.IconStyle

@Inject
class RealBottomNavUI(
    private val screenFactory: ScreenFactory
) : BottomNavUI {
    @Composable
    override fun Content(selectedIndex: Int, onSelectedIndex: (index: Int, screen: Screen) -> Unit) {
        val items = remember(Unit) {
            listOf(
                BottomNavItem.HOME,
                BottomNavItem.SEARCH,
                BottomNavItem.POST,
                BottomNavItem.INBOX,
                BottomNavItem.PROFILE
            )
        }

        val interactionSource = remember { MutableInteractionSource() }

        Surface(
            shadowElevation = 8.dp,
            modifier = Modifier.fillMaxWidth().background(Color.White)
        ) {


            Row(
                modifier = Modifier.fillMaxWidth().background(Color.White).padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                items.forEachIndexed { index, bottomNavItem ->
                    val isSelected = selectedIndex == index

                    Box(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            val screen: Screen = when (bottomNavItem) {
                                BottomNavItem.HOME -> screenFactory.homeScreen()
                                BottomNavItem.SEARCH -> screenFactory.searchScreen()
                                BottomNavItem.POST -> screenFactory.postScreen()
                                BottomNavItem.INBOX -> screenFactory.messagesScreen()
                                BottomNavItem.PROFILE -> screenFactory.profileScreen()
                            }

                            onSelectedIndex(index, screen)
                        }
                    ) {
                        CarveIcon(
                            modifier = Modifier.size(30.dp),
                            icon = bottomNavItem.icon,
                            style = if (isSelected) IconStyle.BOLD else IconStyle.LIGHT_OUTLINE
                        )
                    }
                }

            }

        }
    }
}