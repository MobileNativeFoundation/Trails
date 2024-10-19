package org.mobilenativefoundation.trails.xplat.feat.searchScreen.impl

import androidx.compose.runtime.Composable
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.feat.searchScreen.api.SearchScreen

@Inject
class SearchScreenPresenter : SearchScreen.Presenter {

    @Composable
    override fun present(): SearchScreen.State {
        return SearchScreen.State
    }
}