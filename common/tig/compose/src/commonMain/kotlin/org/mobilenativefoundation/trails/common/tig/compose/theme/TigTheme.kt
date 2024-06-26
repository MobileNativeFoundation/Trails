package org.mobilenativefoundation.trails.common.tig.compose.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import org.mobilenativefoundation.trails.common.tig.compose.theme.color.LocalColorScheme
import org.mobilenativefoundation.trails.common.tig.compose.theme.typography.LocalTypography

@Composable
fun TigTheme(
    colorScheme: ColorScheme = TIG.ColorScheme,
    shapes: Shapes = TrailsShapes,
    typography: Typography = TIG.Typography,
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides typography
    ) {
        MaterialTheme(colorScheme = colorScheme, typography = typography, shapes = shapes, content = content)
    }
}