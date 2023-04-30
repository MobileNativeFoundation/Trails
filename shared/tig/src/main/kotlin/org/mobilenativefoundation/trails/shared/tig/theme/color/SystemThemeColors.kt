package org.mobilenativefoundation.trails.shared.tig.theme.color

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.luminance

@ReadOnlyComposable
@Composable
fun systemColorScheme(): ColorScheme = TrailsColors(!isSystemInDarkTheme()).asColorScheme()

@ReadOnlyComposable
@Composable
fun darkColorScheme() = TrailsColors(isLight = false).asColorScheme()

@ReadOnlyComposable
@Composable
fun lightColorScheme() = TrailsColors(isLight = true).asColorScheme()


private val ColorScheme.isLight
    @Composable
    get() = this.background.luminance() > 0.5

@Composable
fun isLight() = MaterialTheme.colorScheme.isLight