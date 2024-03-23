package org.mobilenativefoundation.trails.common.core.impl

import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.common.core.api.CoreComponent
import org.mobilenativefoundation.trails.common.core.api.TrailsScaffold

@Component
abstract class RealCoreComponent : CoreComponent {
    @Provides
    fun bindPresenterFactory(impl: TrailsPresenterFactory): Presenter.Factory = impl

    @Provides
    fun bindUiFactory(impl: TrailsUiFactory): Ui.Factory = impl

    @Provides
    fun bindTrailsScaffold(impl: RealTrailsScaffold): TrailsScaffold = impl
}