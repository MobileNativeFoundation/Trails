package org.mobilenativefoundation.trails.xplat.core.circuit.impl

import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.xplat.core.circuit.api.CircuitComponent
import org.mobilenativefoundation.trails.xplat.core.circuit.api.ScreenFactory
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreenComponent

@Component
abstract class RealCircuitComponent(
    @Component val homeScreenComponent: HomeScreenComponent
) : CircuitComponent {
    @Provides
    fun bindScreenFactory(impl: RealScreenFactory): ScreenFactory = impl

    @Provides
    fun bindPresenterFactory(impl: PresenterFactory): Presenter.Factory = impl

    @Provides
    fun bindUIFactory(impl: UIFactory): Ui.Factory = impl

    companion object
}