package org.mobilenativefoundation.trails.xplat.feat.searchScreen.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.xplat.feat.searchScreen.api.SearchScreen
import org.mobilenativefoundation.trails.xplat.feat.searchScreen.api.SearchScreenComponent

@Component
abstract class RealSearchScreenComponent : SearchScreenComponent {
    @Provides
    fun bindSearchScreenUI(impl: SearchScreenUI): SearchScreen.UI = impl

    @Provides
    fun bindSearchScreenPresenter(impl: SearchScreenPresenter): SearchScreen.Presenter = impl

    override val searchScreen: SearchScreen = RealSearchScreen

    companion object
}