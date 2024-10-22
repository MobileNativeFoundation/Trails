package org.mobilenativefoundation.trails.xplat.lib.carve.material3

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.mobilenativefoundation.trails.xplat.lib.carve.foundation.Colors


internal object CarveColorSchemes {

    private val LightPrimary = Color(0xFF1536FF)
    private val LightSurface = Color(0xFFF0F1F3)
    private val LightNeutral = Color(0xFF979797)

    private val DarkPrimary = Color(0xFF8B9EFF)
    private val DarkSurface = Color(0xFF1A1C1E)
    private val DarkNeutral = Color(0xFFBFBFBF)

    val Light = lightColorScheme(
        primary = LightPrimary,
        onPrimary = Colors.WHITE,
        primaryContainer = Colors.LIGHT_BLUE_1200,
        onPrimaryContainer = Colors.LIGHT_BLUE_100,
        secondary = Colors.LIGHT_GRAY_600,
        onSecondary = Colors.WHITE,
        secondaryContainer = Colors.LIGHT_GRAY_200,
        onSecondaryContainer = Colors.LIGHT_GRAY_800,
        tertiary = Colors.LIGHT_INDIGO_700,
        onTertiary = Colors.WHITE,
        tertiaryContainer = Colors.LIGHT_INDIGO_100,
        onTertiaryContainer = Colors.LIGHT_INDIGO_900,
        error = Colors.LIGHT_RED_1000,
        errorContainer = Colors.LIGHT_RED_300,
        onError = Colors.WHITE,
        onErrorContainer = Colors.LIGHT_RED_1400,
        background = Colors.WHITE,
        onBackground = Colors.BLACK,
        surface = LightSurface,
        onSurface = Colors.BLACK,
        surfaceVariant = Colors.LIGHT_GRAY_100,
        onSurfaceVariant = Colors.LIGHT_GRAY_700,
        outline = Colors.LIGHT_GRAY_500,
        inverseOnSurface = Colors.WHITE,
        inverseSurface = Colors.DARK_GRAY_800,
        inversePrimary = Colors.LIGHT_BLUE_300,
        surfaceTint = LightPrimary,
        outlineVariant = Colors.LIGHT_GRAY_300,
        scrim = Colors.LIGHT_GRAY_900
    )

    val Dark = darkColorScheme(
        primary = DarkPrimary,
        onPrimary = Colors.DARK_BLUE_100,
        primaryContainer = Colors.DARK_BLUE_800,
        onPrimaryContainer = Colors.DARK_BLUE_200,
        secondary = Colors.DARK_GRAY_300,
        onSecondary = Colors.DARK_GRAY_900,
        secondaryContainer = Colors.DARK_GRAY_700,
        onSecondaryContainer = Colors.DARK_GRAY_100,
        tertiary = Colors.DARK_INDIGO_300,
        onTertiary = Colors.DARK_INDIGO_900,
        tertiaryContainer = Colors.DARK_INDIGO_700,
        onTertiaryContainer = Colors.DARK_INDIGO_100,
        error = Colors.DARK_RED_1100,
        errorContainer = Colors.DARK_RED_300,
        onError = Colors.DARK_RED_200,
        onErrorContainer = Colors.DARK_RED_1300,
        background = DarkSurface,
        onBackground = Colors.WHITE,
        surface = DarkSurface,
        onSurface = Colors.WHITE,
        surfaceVariant = Colors.DARK_GRAY_200,
        onSurfaceVariant = Colors.DARK_GRAY_600,
        outline = Colors.DARK_GRAY_500,
        inverseOnSurface = Colors.BLACK,
        inverseSurface = Colors.LIGHT_GRAY_200,
        inversePrimary = LightPrimary,
        surfaceTint = DarkPrimary,
        outlineVariant = Colors.DARK_GRAY_700,
        scrim = Colors.DARK_GRAY_900
    )

    @Composable
    fun systemColorScheme(): ColorScheme {
        return if (isSystemInDarkTheme()) {
            Dark
        } else {
            Light
        }
    }
}

