package org.mobilenativefoundation.trails.xplat.feat.bottomNav.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.xplat.core.circuit.api.CircuitComponent
import org.mobilenativefoundation.trails.xplat.feat.bottomNav.api.BottomNavComponent
import org.mobilenativefoundation.trails.xplat.feat.bottomNav.api.BottomNavUI

@Component
abstract class RealBottomNavComponent(
    @Component val circuitComponent: CircuitComponent
) : BottomNavComponent {

    @Provides
    fun bindBottomNavUI(impl: RealBottomNavUI): BottomNavUI = impl

    companion object
}