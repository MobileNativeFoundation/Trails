package org.mobilenativefoundation.trails.common.homeTab.api

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.presenter.Presenter as CircuitPresenter
import com.slack.circuit.runtime.ui.Ui as CircuitUi

interface HomeScreen : Screen {
    object State : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        class ClickNavItem(val index: Int) : Event
    }

    interface Ui : CircuitUi<State>
    interface Presenter : CircuitPresenter<State>
}