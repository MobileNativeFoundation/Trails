package org.mobilenativefoundation.trails.xplat.core.circuit.api

import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen

interface ScreenFactory {
    fun homeScreen(): HomeScreen
}