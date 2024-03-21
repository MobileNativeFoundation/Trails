package org.mobilenativefoundation.trails.common.navigation.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.common.navigation.api.BottomNavBar
import org.mobilenativefoundation.trails.common.navigation.api.NavigationComponent

@Component
abstract class RealNavigationComponent : NavigationComponent {
    @Provides
    fun bindBottomNavBar(impl: RealBottomNavBar): BottomNavBar = impl

    companion object
}