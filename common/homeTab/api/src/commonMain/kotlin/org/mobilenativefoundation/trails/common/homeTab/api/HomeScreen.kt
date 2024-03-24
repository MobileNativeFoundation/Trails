package org.mobilenativefoundation.trails.common.homeTab.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen

interface HomeScreen : Screen {
    object State : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        class ClickNavItem(val index: Int) : Event
    }

    interface Ui {
        @Composable
        fun Content(state: State, modifier: Modifier)
    }

    interface Presenter {
        @Composable
        operator fun invoke(): State
    }
}