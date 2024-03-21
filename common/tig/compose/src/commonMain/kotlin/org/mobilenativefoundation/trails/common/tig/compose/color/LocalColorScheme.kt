package org.mobilenativefoundation.trails.common.tig.compose.color

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalColorScheme: ProvidableCompositionLocal<ColorScheme>
    @ReadOnlyComposable
    @Composable
    get() {
        val colorScheme = systemColorScheme()
        return staticCompositionLocalOf { colorScheme }
    }