package org.mobilenativefoundation.trails.xplat.feat.homeScreen.api

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.presenter.Presenter as CircuitPresenter
import com.slack.circuit.runtime.ui.Ui as CircuitUI

interface HomeScreen : Screen {
    data class State(
        val posts: List<String>
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        class ClickNavItem(val index: Int) : Event
    }

    interface UI : CircuitUI<State>
    interface Presenter : CircuitPresenter<State>
}