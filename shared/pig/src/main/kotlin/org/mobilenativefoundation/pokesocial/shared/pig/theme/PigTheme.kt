package org.mobilenativefoundation.pokesocial.shared.pig.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.LocalColorScheme
import org.mobilenativefoundation.pokesocial.shared.pig.theme.shape.PokesocialShapes
import org.mobilenativefoundation.pokesocial.shared.pig.theme.typography.LocalTypography


@Composable
fun PigTheme(
    colorScheme: ColorScheme = PIG.ColorScheme,
    shapes: Shapes = PokesocialShapes,
    typography: Typography = PIG.Typography,
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides typography
    ) {
        MaterialTheme(colorScheme = colorScheme, typography = typography, shapes = shapes, content = content)
    }
}

