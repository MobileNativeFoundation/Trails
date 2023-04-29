package org.mobilenativefoundation.pokesocial.shared.pig.theme


import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.LocalColorScheme
import org.mobilenativefoundation.pokesocial.shared.pig.theme.typography.LocalTypography


object PIG {
    val ColorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val Typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}