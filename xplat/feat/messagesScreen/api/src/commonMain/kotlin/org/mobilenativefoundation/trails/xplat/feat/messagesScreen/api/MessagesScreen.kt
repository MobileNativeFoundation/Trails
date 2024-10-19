package org.mobilenativefoundation.trails.xplat.feat.messagesScreen.api

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.presenter.Presenter as CircuitPresenter
import com.slack.circuit.runtime.ui.Ui as CircuitUI

interface MessagesScreen : Screen {
    object State : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        class ClickNavItem(val index: Int) : Event
    }

    interface UI : CircuitUI<State>
    interface Presenter : CircuitPresenter<State>
}