package org.mobilenativefoundation.trails.common.tig.compose

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import org.mobilenativefoundation.trails.common.tig.compose.color.LocalColorScheme
import org.mobilenativefoundation.trails.common.tig.compose.typography.LocalTypography


object TIG {
    val ColorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val Typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}