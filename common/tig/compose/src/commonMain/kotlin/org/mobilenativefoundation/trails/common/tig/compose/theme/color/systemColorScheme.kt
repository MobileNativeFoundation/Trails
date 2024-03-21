package org.mobilenativefoundation.trails.common.tig.compose.theme.color

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import asColorScheme
import trailsColors

@ReadOnlyComposable
@Composable
fun systemColorScheme(): ColorScheme = trailsColors(!isSystemInDarkTheme()).asColorScheme()

@ReadOnlyComposable
@Composable
fun darkColorScheme() = trailsColors(isLight = false).asColorScheme()

@ReadOnlyComposable
@Composable
fun lightColorScheme() = trailsColors(isLight = true).asColorScheme()
