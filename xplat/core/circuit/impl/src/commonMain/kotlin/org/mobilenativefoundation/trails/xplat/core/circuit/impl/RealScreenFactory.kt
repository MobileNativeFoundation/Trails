package org.mobilenativefoundation.trails.xplat.core.circuit.impl

import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.core.circuit.api.ScreenFactory
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen
import org.mobilenativefoundation.trails.xplat.feat.messagesScreen.api.MessagesScreen
import org.mobilenativefoundation.trails.xplat.feat.searchScreen.api.SearchScreen

@Inject
class RealScreenFactory(
    private val homeScreen: HomeScreen,
    private val messagesScreen: MessagesScreen,
    private val searchScreen: SearchScreen
) : ScreenFactory {
    override fun homeScreen(): HomeScreen {
        return homeScreen
    }

    override fun messagesScreen(): MessagesScreen {
        return messagesScreen
    }

    override fun searchScreen(): SearchScreen {
        return searchScreen
    }
}