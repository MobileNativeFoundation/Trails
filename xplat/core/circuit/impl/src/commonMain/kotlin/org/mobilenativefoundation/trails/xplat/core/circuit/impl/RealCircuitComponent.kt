package org.mobilenativefoundation.trails.xplat.core.circuit.impl

import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.xplat.core.circuit.api.CircuitComponent
import org.mobilenativefoundation.trails.xplat.core.circuit.api.ScreenFactory
import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.messagesScreen.api.MessagesScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.postScreen.api.PostScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.profileScreen.api.ProfileScreenComponent
import org.mobilenativefoundation.trails.xplat.feat.searchScreen.api.SearchScreenComponent

@Component
abstract class RealCircuitComponent(
    @Component val homeScreenComponent: HomeScreenComponent,
    @Component val messagesScreenComponent: MessagesScreenComponent,
    @Component val postScreenComponent: PostScreenComponent,
    @Component val searchScreenComponent: SearchScreenComponent,
    @Component val profileScreenComponent: ProfileScreenComponent
) : CircuitComponent {
    @Provides
    fun bindScreenFactory(impl: RealScreenFactory): ScreenFactory = impl

    @Provides
    fun bindPresenterFactory(impl: PresenterFactory): Presenter.Factory = impl

    @Provides
    fun bindUIFactory(impl: UIFactory): Ui.Factory = impl

    companion object
}