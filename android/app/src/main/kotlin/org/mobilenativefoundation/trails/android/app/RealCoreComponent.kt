package org.mobilenativefoundation.trails.android.app

import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.common.bookmarksTab.api.BookmarksComponent
import org.mobilenativefoundation.trails.common.core.api.CoreComponent
import org.mobilenativefoundation.trails.common.core.api.ScreenFactory
import org.mobilenativefoundation.trails.common.core.impl.RealScreenFactory
import org.mobilenativefoundation.trails.common.core.impl.TrailsPresenterFactory
import org.mobilenativefoundation.trails.common.core.impl.TrailsUiFactory
import org.mobilenativefoundation.trails.common.hikeTab.api.HikeComponent
import org.mobilenativefoundation.trails.common.homeTab.api.HomeComponent
import org.mobilenativefoundation.trails.common.profileTab.api.ProfileComponent
import org.mobilenativefoundation.trails.common.searchTab.api.SearchComponent

@Component
abstract class RealCoreComponent(
    @Component val bookmarksComponent: BookmarksComponent,
    @Component val hikeComponent: HikeComponent,
    @Component val homeComponent: HomeComponent,
    @Component val profileComponent: ProfileComponent,
    @Component val searchComponent: SearchComponent,
) : CoreComponent {

    @Provides
    fun bindPresenterFactory(impl: TrailsPresenterFactory): Presenter.Factory = impl

    @Provides
    fun bindUiFactory(impl: TrailsUiFactory): Ui.Factory = impl

    @Provides
    fun bindScreenFactory(impl: RealScreenFactory): ScreenFactory = impl

    companion object
}