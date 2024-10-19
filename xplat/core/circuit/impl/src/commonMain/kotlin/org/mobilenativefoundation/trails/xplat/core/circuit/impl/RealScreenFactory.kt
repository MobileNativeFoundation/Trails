package org.mobilenativefoundation.trails.xplat.core.circuit.impl

import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.core.circuit.api.ScreenFactory
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen

@Inject
class RealScreenFactory(
    private val homeScreen: HomeScreen,
) : ScreenFactory {
    override fun homeScreen(): HomeScreen {
        return homeScreen
    }
}