package org.mobilenativefoundation.trails.xplat.feat.homeScreen.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreenComponent
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.api.PostComponent

@Component
abstract class RealHomeScreenComponent(
    @Component val postComponent: PostComponent
) : HomeScreenComponent {
    @Provides
    fun bindHomeScreenUI(impl: HomeScreenUI): HomeScreen.UI = impl

    @Provides
    fun bindHomeScreenPresenter(impl: HomeScreenPresenter): HomeScreen.Presenter = impl

    override val homeScreen: HomeScreen = RealHomeScreen

    companion object
}