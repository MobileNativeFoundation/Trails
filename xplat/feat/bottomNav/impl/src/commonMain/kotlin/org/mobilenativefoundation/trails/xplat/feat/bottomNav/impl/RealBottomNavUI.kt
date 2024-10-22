package org.mobilenativefoundation.trails.xplat.feat.bottomNav.impl

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
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
                BottomNavItem.HOME,
                BottomNavItem.SEARCH,
                BottomNavItem.POST,
                BottomNavItem.INBOX,
                BottomNavItem.PROFILE
            )
        }

        val outlineColor = Carve.ColorScheme.outline
        val interactionSource = remember { MutableInteractionSource() }

        NavigationBar(
            containerColor = Carve.ColorScheme.surface,
            modifier = Modifier.drawBehind {

                drawLine(
                    outlineColor,
                    Offset(0f, 0f),
                    Offset(size.width, 0f),
                    0.1f
                )
            }
        ) {

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
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