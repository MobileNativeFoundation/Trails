package org.mobilenativefoundation.trails.common.navigation.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.common.core.api.CoreComponent
import org.mobilenativefoundation.trails.common.navigation.api.BottomNavBar
import org.mobilenativefoundation.trails.common.navigation.api.NavigationComponent

@Component
abstract class RealNavigationComponent(
    @Component val coreComponent: CoreComponent
) : NavigationComponent {

    @Provides
    fun bindBottomNavBar(impl: RealBottomNavBar): BottomNavBar = impl

    companion object
}