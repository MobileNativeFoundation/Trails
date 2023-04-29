package org.mobilenativefoundation.pokesocial.shared.pig.theme.color

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

@ReadOnlyComposable
@Composable
fun systemColorScheme(): ColorScheme = PokesocialColors(!isSystemInDarkTheme()).asColorScheme()

@ReadOnlyComposable
@Composable
fun darkColorScheme() = PokesocialColors(isLight = false).asColorScheme()

@ReadOnlyComposable
@Composable
fun lightColorScheme() = PokesocialColors(isLight = true).asColorScheme()
