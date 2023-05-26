package org.mobilenativefoundation.trails.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import org.mobilenativefoundation.trails.android.common.wiring.ComponentHolder
import org.mobilenativefoundation.trails.android.ui.Scaffold
import org.mobilenativefoundation.trails.android.wiring.UserComponent
import org.mobilenativefoundation.trails.android.wiring.UserDependencies
import org.mobilenativefoundation.trails.shared.tig.theme.TigTheme
import org.mobilenativefoundation.trails.shared.timeline.TimelineViewModel
import org.mobilenativefoundation.trails.shared.timeline.TimelineViewModelFactory

class MainActivity : ComponentActivity(), ComponentHolder {

    private val scope: CoroutineScope = CoroutineScope(Main)

    override lateinit var component: UserComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appComponent = (application as TrailsApp).component
        component = (appComponent as UserComponent.ParentBindings).userComponentFactory().create()

        setContent {
            val navHostController = rememberNavController()

            TigTheme {
                Scaffold(
                    modifier = Modifier,
                    navHostController = navHostController,
                    userComponent = component,
                    appComponent = appComponent
                )
            }
        }
    }
}

