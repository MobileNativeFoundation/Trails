@file:OptIn(ExperimentalMaterial3Api::class)

package org.mobilenativefoundation.pokesocial.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mobilenativefoundation.pokesocial.android.R
import org.mobilenativefoundation.pokesocial.shared.pig.theme.PigTheme
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.PokesocialColors
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.darkColorScheme
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.isLight
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.lightColorScheme

@Composable
fun TopBar(
    title: String,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: (@Composable() (RowScope.() -> Unit))? = null,
) {
    TopBarUI(title, navigationIcon, actions)
}


@Composable
fun PokesocialLogo(size: Int = 32) {
    val resId = if (isLight()) R.drawable.logo_light else R.drawable.logo_dark

    Icon(
        painter = painterResource(resId),
        "Pokesocial Logo",
        modifier = Modifier.size(size.dp),
        tint = Color.Unspecified
    )
}

@Composable
fun SearchIcon(size: Int = 32) {

    val pokesocialColors = PokesocialColors()
    Icon(
        painter = painterResource(R.drawable.search_light),
        "Search",
        modifier = Modifier.size(size.dp),
        tint = if (isLight()) pokesocialColors.gray900 else pokesocialColors.white
    )
}

@Composable
private fun TopBarUI(
    title: String,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: (@Composable() (RowScope.() -> Unit))? = null
) {

    val pokesocialColors = PokesocialColors()

    TopAppBar(
        title = {
            Text(text = title, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(horizontal = 8.dp))
        },
        modifier = Modifier,
        navigationIcon = navigationIcon ?: {},
        actions = actions ?: {},
        windowInsets = WindowInsets(left = 0.dp, top = 0.dp, right = 0.dp, bottom = 0.dp),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
    )
}

@Preview
@Composable
private fun StandardPreview() {
    Column {
        PigTheme(lightColorScheme()) {
            Surface {
                Column {
                    TopBarUI(
                        title = "Trending",
                        navigationIcon = { PokesocialLogo() },
                        actions = {
                            SearchIcon()
                        }
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
                    TopBarUI(
                        title = "Trending",
                        navigationIcon = { PokesocialLogo() },
                        actions = {
                            SearchIcon()
                        }
                    )
                }
            }
        }
    }
}
