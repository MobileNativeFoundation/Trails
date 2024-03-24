package org.mobilenativefoundation.trails.common.core.api

import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui

interface CoreComponent {
    val presenterFactory: Presenter.Factory
    val uiFactory: Ui.Factory
    val trailsScaffold: TrailsScaffold
    val screenFactory: ScreenFactory
}