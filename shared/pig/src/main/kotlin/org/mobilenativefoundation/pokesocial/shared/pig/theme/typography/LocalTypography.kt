package org.mobilenativefoundation.pokesocial.shared.pig.theme.typography

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalTypography: ProvidableCompositionLocal<Typography>
    @ReadOnlyComposable
    @Composable
    get() {
        return staticCompositionLocalOf { PokedexTypography }
    }