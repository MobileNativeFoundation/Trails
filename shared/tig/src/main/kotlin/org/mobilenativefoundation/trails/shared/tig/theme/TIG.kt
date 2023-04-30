package org.mobilenativefoundation.trails.shared.tig.theme


import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import org.mobilenativefoundation.trails.shared.tig.theme.color.LocalColorScheme
import org.mobilenativefoundation.trails.shared.tig.theme.typography.LocalTypography


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