package org.mobilenativefoundation.trails.xplat.feat.profileScreen.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.xplat.feat.profileScreen.api.ProfileScreen
import org.mobilenativefoundation.trails.xplat.feat.profileScreen.api.ProfileScreenComponent

@Component
abstract class RealProfileScreenComponent : ProfileScreenComponent {
    @Provides
    fun bindProfileScreenUI(impl: ProfileScreenUI): ProfileScreen.UI = impl

    @Provides
    fun bindProfileScreenPresenter(impl: ProfileScreenPresenter): ProfileScreen.Presenter = impl

    override val profileScreen: ProfileScreen = RealProfileScreen

    companion object
}