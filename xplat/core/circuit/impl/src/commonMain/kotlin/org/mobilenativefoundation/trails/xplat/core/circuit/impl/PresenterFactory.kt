package org.mobilenativefoundation.trails.xplat.core.circuit.impl

import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen

@Inject
class PresenterFactory(
    private val homeScreenPresenter: HomeScreen.Presenter
) : Presenter.Factory {
    override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
        return when (screen) {
            is HomeScreen -> homeScreenPresenter
            else -> null
        }
    }
}