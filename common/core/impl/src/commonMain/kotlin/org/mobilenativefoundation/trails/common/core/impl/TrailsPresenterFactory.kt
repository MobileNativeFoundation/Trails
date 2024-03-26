package org.mobilenativefoundation.trails.common.core.impl

import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.presenter.presenterOf
import com.slack.circuit.runtime.screen.Screen
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.bookmarksTab.api.BookmarksScreen
import org.mobilenativefoundation.trails.common.hikeTab.api.HikeScreen
import org.mobilenativefoundation.trails.common.homeTab.api.HomeScreen
import org.mobilenativefoundation.trails.common.profileTab.api.ProfileScreen
import org.mobilenativefoundation.trails.common.searchTab.api.SearchScreen

@Inject
class TrailsPresenterFactory(
    private val homeScreenPresenter: HomeScreen.Presenter,
    private val bookmarksScreenPresenter: BookmarksScreen.Presenter,
    private val hikeScreenPresenter: HikeScreen.Presenter,
    private val profileScreenPresenter: ProfileScreen.Presenter,
    private val searchScreenPresenter: SearchScreen.Presenter
) : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*>? {
        return when (screen) {
            is BookmarksScreen -> presenterOf { bookmarksScreenPresenter() }
            is HomeScreen -> presenterOf { homeScreenPresenter() }
            is HikeScreen -> presenterOf { hikeScreenPresenter() }
            is ProfileScreen -> presenterOf { profileScreenPresenter() }
            is SearchScreen -> presenterOf { searchScreenPresenter() }
            else -> null
        }
    }

}