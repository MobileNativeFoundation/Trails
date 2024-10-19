package org.mobilenativefoundation.trails.xplat.core.circuit.impl

import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen

@Inject
class UIFactory(
    private val homeScreenUI: HomeScreen.UI
) : Ui.Factory {

    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return when (screen) {
            is HomeScreen -> ui<HomeScreen.State> { state, modifier ->
                homeScreenUI.Content(state, modifier)
            }

            else -> null
        }
    }

}