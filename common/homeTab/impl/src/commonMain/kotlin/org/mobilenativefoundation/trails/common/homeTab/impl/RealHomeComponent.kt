package org.mobilenativefoundation.trails.common.homeTab.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.common.homeTab.api.HomeComponent
import org.mobilenativefoundation.trails.common.homeTab.api.HomeScreen


@Component
abstract class RealHomeComponent : HomeComponent {
    @Provides
    fun bindHomeScreenUi(impl: HomeScreenUi): HomeScreen.Ui = impl

    @Provides
    fun bindHomeScreenPresenter(impl: HomeScreenPresenter): HomeScreen.Presenter = impl

    companion object
}