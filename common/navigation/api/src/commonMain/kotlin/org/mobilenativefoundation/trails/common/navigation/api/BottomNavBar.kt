package org.mobilenativefoundation.trails.common.navigation.api

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.screen.Screen

interface BottomNavBar {
    @Composable
    fun Content(selectedIndex: Int, onSelectedIndex: (index: Int, screen: Screen) -> Unit)
}