package org.mobilenativefoundation.pokesocial.android.common.pig

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import org.mobilenativefoundation.pokesocial.android.common.pig.color.LocalColorScheme
import org.mobilenativefoundation.pokesocial.android.common.pig.shape.PokesocialShapes


@Composable
fun PigTheme(
    colorScheme: ColorScheme = PIG.ColorScheme,
    shapes: Shapes = PokesocialShapes,
    typography: Typography = PIG.Typography,
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
    ) {
        MaterialTheme(colorScheme = colorScheme, typography = typography, shapes = shapes, content = content)
    }
}

