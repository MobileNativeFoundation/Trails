package org.mobilenativefoundation.trails.xplat.core.circuit.impl

import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen
import org.mobilenativefoundation.trails.xplat.feat.messagesScreen.api.MessagesScreen
import org.mobilenativefoundation.trails.xplat.feat.postScreen.api.PostScreen
import org.mobilenativefoundation.trails.xplat.feat.searchScreen.api.SearchScreen

@Inject
class UIFactory(
    private val homeScreenUI: HomeScreen.UI,
    private val messagesScreenUI: MessagesScreen.UI,
    private val postScreenUI: PostScreen.UI,
    private val searchScreenUI: SearchScreen.UI
) : Ui.Factory {

    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return when (screen) {
            is HomeScreen -> ui<HomeScreen.State> { state, modifier ->
                homeScreenUI.Content(state, modifier)
            }

            is SearchScreen -> ui<SearchScreen.State> { state, modifier ->
                searchScreenUI.Content(state, modifier)
            }

            is MessagesScreen -> ui<MessagesScreen.State> { state, modifier ->
                messagesScreenUI.Content(state, modifier)
            }

            is PostScreen -> ui<PostScreen.State> { state, modifier ->
                postScreenUI.Content(state, modifier)
            }

            else -> null
        }
    }

}