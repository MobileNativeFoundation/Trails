package org.mobilenativefoundation.trails.common.hikeTab.api

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.presenter.Presenter as CircuitPresenter
import com.slack.circuit.runtime.ui.Ui as CircuitUi

interface HikeScreen : Screen {
    object State : CircuitUiState

    sealed interface Event : CircuitUiEvent {
    }

    interface Ui : CircuitUi<State>

    interface Presenter : CircuitPresenter<State>
}