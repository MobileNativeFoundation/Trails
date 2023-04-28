package org.mobilenativefoundation.pokesocial.android.common.pig


import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import org.mobilenativefoundation.pokesocial.android.common.pig.color.LocalColorScheme
import org.mobilenativefoundation.pokesocial.android.common.pig.typography.LocalTypography


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