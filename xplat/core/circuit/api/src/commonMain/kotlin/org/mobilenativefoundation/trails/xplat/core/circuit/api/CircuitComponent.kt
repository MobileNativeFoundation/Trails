package org.mobilenativefoundation.trails.xplat.core.circuit.api

import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui

interface CircuitComponent {
    val screenFactory: ScreenFactory
    val presenterFactory: Presenter.Factory
    val uiFactory: Ui.Factory
}