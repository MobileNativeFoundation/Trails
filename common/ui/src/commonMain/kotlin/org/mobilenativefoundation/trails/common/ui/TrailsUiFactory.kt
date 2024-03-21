package org.mobilenativefoundation.trails.common.ui

import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.bookmarksTab.api.BookmarksScreen
import org.mobilenativefoundation.trails.common.hikeTab.api.HikeScreen
import org.mobilenativefoundation.trails.common.homeTab.api.HomeScreen
import org.mobilenativefoundation.trails.common.profileTab.api.ProfileScreen
import org.mobilenativefoundation.trails.common.searchTab.api.SearchScreen

@Inject
class TrailsUiFactory(
    private val homeScreenUi: HomeScreen.Ui,
    private val bookmarksScreenUi: BookmarksScreen.Ui,
    private val searchScreenUi: SearchScreen.Ui,
    private val profileScreenUi: ProfileScreen.Ui,
    private val hikeScreenUi: HikeScreen.Ui,
) : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return when (screen) {
            is HomeScreen -> ui<HomeScreen.State> { state, modifier ->
                homeScreenUi.Content(state, modifier)
            }

            is SearchScreen -> ui<SearchScreen.State> { state, modifier ->
                searchScreenUi.Content(state, modifier)
            }

            is HikeScreen -> ui<HikeScreen.State> { state, modifier ->
                hikeScreenUi.Content(state, modifier)
            }

            is BookmarksScreen -> ui<BookmarksScreen.State> { state, modifier ->
                bookmarksScreenUi.Content(state, modifier)
            }

            is ProfileScreen -> ui<ProfileScreen.State> { state, modifier ->
                profileScreenUi.Content(state, modifier)
            }

            else -> null
        }
    }
}