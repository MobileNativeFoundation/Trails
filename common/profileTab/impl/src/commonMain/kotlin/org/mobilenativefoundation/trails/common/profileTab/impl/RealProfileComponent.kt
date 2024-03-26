package org.mobilenativefoundation.trails.common.profileTab.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.common.profileTab.api.ProfileComponent
import org.mobilenativefoundation.trails.common.profileTab.api.ProfileScreen

@Component
abstract class RealProfileComponent : ProfileComponent {
    @Provides
    fun bindProfileScreenUi(impl: ProfileScreenUi): ProfileScreen.Ui = impl

    @Provides
    fun bindProfileScreenPresenter(impl: ProfileScreenPresenter): ProfileScreen.Presenter = impl

    @Provides
    fun provideProfileScreen(): ProfileScreen = RealProfileScreen

    companion object
}