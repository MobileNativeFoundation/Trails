package org.mobilenativefoundation.pokesocial.android.ui

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
import org.mobilenativefoundation.pokesocial.shared.navigation.BottomTabs
import org.mobilenativefoundation.pokesocial.shared.navigation.Screen
import org.mobilenativefoundation.pokesocial.shared.pig.theme.PigTheme
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.PokesocialColors
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.darkColorScheme
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.isLight
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.lightColorScheme
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.systemColorScheme

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    fun isSelected(tab: Screen) = currentDestination?.hierarchy?.any { it.route == tab.route } == true

    BottomBarUI(
        ::isSelected,
        navController::navigate
    )
}


@Composable
private fun BottomBarUI(
    isSelected: (tab: Screen) -> Boolean,
    navigate: (route: String) -> Unit
) {

    val colorScheme = systemColorScheme()
    val pokesocialColors = PokesocialColors()

    BottomAppBar(
        containerColor = if (isLight()) pokesocialColors.white else pokesocialColors.dark1.copy(alpha = 0.85f),
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
                            tint = if (selected) colorScheme.primary else pokesocialColors.gray500
                        )
                    }
                }
            }
        },
    )
}


@Preview
@Composable
private fun StandardPreview() {
    Column {
        PigTheme(lightColorScheme()) {
            Surface {
                Column {
                    BottomBarUI(
                        isSelected = { tab -> tab.route == Screen.Discover.route },
                        navigate = { _ -> }
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
        PigTheme(darkColorScheme()) {
            Surface {
                Column {
                    BottomBarUI(
                        isSelected = { tab -> tab.route == Screen.Discover.route },
                        navigate = { _ -> }
                    )
                }
            }
        }
    }
}

