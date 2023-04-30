package org.mobilenativefoundation.trails.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.mobilenativefoundation.trails.shared.navigation.BottomTabs
import org.mobilenativefoundation.trails.shared.navigation.Screen
import org.mobilenativefoundation.trails.shared.tig.theme.TigTheme
import org.mobilenativefoundation.trails.shared.tig.theme.color.TrailsColors
import org.mobilenativefoundation.trails.shared.tig.theme.color.darkColorScheme
import org.mobilenativefoundation.trails.shared.tig.theme.color.isLight
import org.mobilenativefoundation.trails.shared.tig.theme.color.lightColorScheme
import org.mobilenativefoundation.trails.shared.tig.theme.color.systemColorScheme

@Composable
fun BottomBar(navHostController: NavHostController, create: () -> Unit) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    fun isSelected(tab: Screen) = currentDestination?.hierarchy?.any { it.route == tab.route } == true

    BottomBarUI(
        isSelected = ::isSelected,
        navigate = navHostController::navigate,
        create = create
    )
}


@Composable
private fun BottomBarUI(
    isSelected: (tab: Screen) -> Boolean,
    navigate: (route: String) -> Unit,
    create: () -> Unit
) {

    val colorScheme = systemColorScheme()
    val trailsColors = TrailsColors()

    BottomAppBar(
        containerColor = if (isLight()) trailsColors.white else trailsColors.dark1.copy(alpha = 0.85f),
        actions = {
            BottomTabs.forEach { tab ->

                val selected = isSelected(tab)

                Box {

                    val icon = if (selected) tab.iconSelected else tab.iconNotSelected

                    IconButton(onClick = {
                        navigate(tab.route)
                    }) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp),
                            tint = if (selected) colorScheme.primary else trailsColors.gray500
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FAB(create)
        }
    )
}


@Preview
@Composable
private fun StandardPreview() {
    Column {
        TigTheme(lightColorScheme()) {
            Surface {
                Column {
                    BottomBarUI(
                        isSelected = { tab -> tab.route == Screen.Saved.route },
                        navigate = { _ -> },
                        create = {}
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun InversePreview() {
    Column {
        TigTheme(darkColorScheme()) {
            Surface {
                Column {
                    BottomBarUI(
                        isSelected = { tab -> tab.route == Screen.Saved.route },
                        navigate = { _ -> },
                        create = {}
                    )
                }
            }
        }
    }
}

