package org.mobilenativefoundation.trails.common.tig.compose.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import org.mobilenativefoundation.trails.common.tig.compose.theme.color.LocalColorScheme
import org.mobilenativefoundation.trails.common.tig.compose.theme.typography.LocalTypography


object TIG {
    val ColorScheme: ColorScheme
        @Composable
        get() = LocalColorScheme.current

    val Typography: Typography
        @Composable
        get() = LocalTypography.current
}