package org.mobilenativefoundation.trails.common.searchTab.impl

import androidx.compose.runtime.Composable
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.common.searchTab.api.SearchScreen

@Inject
class SearchScreenPresenter : SearchScreen.Presenter {
    @Composable
    override fun present(): SearchScreen.State {
        return SearchScreen.State
    }

}