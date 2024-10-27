package org.mobilenativefoundation.trails.xplat.lib.carve.material3

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import org.mobilenativefoundation.trails.xplat.lib.carve.material3.CarveColorSchemes.systemColorScheme


object Carve {
    val ColorScheme: ColorScheme
        @Composable
        get() = LocalColorScheme.current

    val Typography: Typography
        @Composable
        get() = LocalTypography.current
}

@Composable
fun Carve(
    typography: Typography = CarveTypography.value,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides typography
    ) {
        MaterialTheme(colorScheme = colorScheme, typography = typography, shapes = shapes, content = content)
    }
}


internal val LocalTypography: ProvidableCompositionLocal<Typography>
    @Composable
    get() {
        val typography = CarveTypography.value
        return staticCompositionLocalOf { typography }
    }


internal val LocalColorScheme: ProvidableCompositionLocal<ColorScheme>
    @Composable
    get() {
        val colorScheme = systemColorScheme()
        return staticCompositionLocalOf { colorScheme }
    }