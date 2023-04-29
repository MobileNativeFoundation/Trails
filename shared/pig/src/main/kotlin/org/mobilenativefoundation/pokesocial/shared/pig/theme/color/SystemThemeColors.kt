package org.mobilenativefoundation.pokesocial.shared.pig.theme.color

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.luminance

@ReadOnlyComposable
@Composable
fun systemColorScheme(): ColorScheme = PokesocialColors(!isSystemInDarkTheme()).asColorScheme()

@ReadOnlyComposable
@Composable
fun darkColorScheme() = PokesocialColors(isLight = false).asColorScheme()

@ReadOnlyComposable
@Composable
fun lightColorScheme() = PokesocialColors(isLight = true).asColorScheme()


private val ColorScheme.isLight
    @Composable
    get() = this.background.luminance() > 0.5

@Composable
fun isLight() = MaterialTheme.colorScheme.isLight