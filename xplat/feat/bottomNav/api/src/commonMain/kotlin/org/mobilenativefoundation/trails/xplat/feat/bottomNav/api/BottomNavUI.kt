package org.mobilenativefoundation.trails.xplat.feat.bottomNav.api

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.screen.Screen

interface BottomNavUI {
    @Composable
    fun Content(selectedIndex: Int, onSelectedIndex: (index: Int, screen: Screen) -> Unit)
}