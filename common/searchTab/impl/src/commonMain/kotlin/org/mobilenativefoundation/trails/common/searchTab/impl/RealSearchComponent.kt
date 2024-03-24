package org.mobilenativefoundation.trails.common.searchTab.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.common.searchTab.api.SearchComponent
import org.mobilenativefoundation.trails.common.searchTab.api.SearchScreen

@Component
abstract class RealSearchComponent : SearchComponent {
    @Provides
    fun bindSearchScreenUi(impl: SearchScreenUi): SearchScreen.Ui = impl

    @Provides
    fun bindSearchScreenPresenter(impl: SearchScreenPresenter): SearchScreen.Presenter = impl

    override val searchScreen: SearchScreen = RealSearchScreen

    companion object
}