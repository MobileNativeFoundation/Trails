package org.mobilenativefoundation.trails.common.tig.compose.color

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

@ReadOnlyComposable
@Composable
fun systemColorScheme(): ColorScheme = TrailsColors(!isSystemInDarkTheme()).asColorScheme()

@ReadOnlyComposable
@Composable
fun darkColorScheme() = TrailsColors(isLight = false).asColorScheme()

@ReadOnlyComposable
@Composable
fun lightColorScheme() = TrailsColors(isLight = true).asColorScheme()