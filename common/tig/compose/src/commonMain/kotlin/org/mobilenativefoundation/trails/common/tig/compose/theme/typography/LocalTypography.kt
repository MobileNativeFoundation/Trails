package org.mobilenativefoundation.trails.common.tig.compose.theme.typography

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalTypography: ProvidableCompositionLocal<Typography>
    @Composable
    get() {
        val typography = TrailsTypography
        return staticCompositionLocalOf { typography }
    }