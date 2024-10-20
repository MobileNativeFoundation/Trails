package org.mobilenativefoundation.trails.xplat.feat.postScreen.impl

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.mobilenativefoundation.trails.xplat.feat.postScreen.api.PostScreen
import org.mobilenativefoundation.trails.xplat.feat.postScreen.api.PostScreenComponent

@Component
abstract class RealPostScreenComponent : PostScreenComponent {
    @Provides
    fun bindPostScreenUI(impl: PostScreenUI): PostScreen.UI = impl

    @Provides
    fun bindPostScreenPresenter(impl: PostScreenPresenter): PostScreen.Presenter = impl

    override val postScreen: PostScreen = RealPostScreen

    companion object
}