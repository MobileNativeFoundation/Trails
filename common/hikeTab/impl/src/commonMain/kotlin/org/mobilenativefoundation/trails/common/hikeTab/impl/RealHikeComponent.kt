package org.mobilenativefoundation.trails.common.hikeTab.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.common.hikeTab.api.HikeComponent
import org.mobilenativefoundation.trails.common.hikeTab.api.HikeScreen

@Component
abstract class RealHikeComponent : HikeComponent {
    @Provides
    fun bindHikeScreenUi(impl: HikeScreenUi): HikeScreen.Ui = impl

    @Provides
    fun bindHikeScreenPresenter(impl: HikeScreenPresenter): HikeScreen.Presenter = impl

    companion object
}