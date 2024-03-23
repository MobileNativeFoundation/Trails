package org.mobilenativefoundation.trails.common.core.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface TrailsScaffold {
    @Composable
    fun Content(modifier: Modifier)
}