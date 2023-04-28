package org.mobilenativefoundation.pokesocial.android.common.pig.color

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import org.mobilenativefoundation.pokesocial.android.common.pig.color.PokesocialColors
import org.mobilenativefoundation.pokesocial.android.common.pig.color.asColorScheme

@ReadOnlyComposable
@Composable
fun systemColorScheme(): ColorScheme = PokesocialColors(!isSystemInDarkTheme()).asColorScheme()

@ReadOnlyComposable
@Composable
fun darkColorScheme() = PokesocialColors(isLight = false).asColorScheme()

@ReadOnlyComposable
@Composable
fun lightColorScheme() = PokesocialColors(isLight = true).asColorScheme()
