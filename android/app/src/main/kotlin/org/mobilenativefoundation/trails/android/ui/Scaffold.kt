@file:OptIn(ExperimentalMaterial3Api::class)

package org.mobilenativefoundation.trails.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import org.mobilenativefoundation.trails.android.wiring.AppComponent
import org.mobilenativefoundation.trails.android.wiring.UserComponent
import org.mobilenativefoundation.trails.shared.navigation.Screen
import androidx.compose.material3.Scaffold as MaterialScaffold

@Composable
fun Scaffold(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    userComponent: UserComponent,
    appComponent: AppComponent
) {

    val topBarTitleState = remember { MutableStateFlow<String?>(null) }
    val topBarNavigationIconState = remember { MutableStateFlow<(@Composable () -> Unit)?>(null) }
    val topBarActionsState = remember { MutableStateFlow<(@Composable() (RowScope.() -> Unit))?>(null) }

    val topBarTitle = topBarTitleState.collectAsState()
    val topBarNavigationIcon = topBarNavigationIconState.collectAsState()
    val topBarActions = topBarActionsState.collectAsState()

    val showBottomBar = remember { mutableStateOf(true) }

    fun showBottomBar() {
        showBottomBar.value = true
    }

    fun hideBottomBar() {
        showBottomBar.value = false
    }

    fun setTopBarTitle(title: String?) {
        topBarTitleState.value = title
    }

    fun setTopBarNavigationIcon(navigationIcon: (@Composable () -> Unit)?) {
        topBarNavigationIconState.value = navigationIcon
    }

    fun setTopBarActions(actions: (@Composable() (RowScope.() -> Unit))?) {
        topBarActionsState.value = actions
    }

    MaterialScaffold(
        modifier = modifier,
        topBar = {
            if (topBarTitle.value != null) {
                TopBar(
                    title = topBarTitle.value,
                    navigationIcon = topBarNavigationIcon.value,
                    actions = topBarActions.value
                )
            }
        },
        bottomBar = {
            if (showBottomBar.value) {
                BottomBar(
                    navHostController = navHostController,
                    create = { navHostController.navigate(Screen.Hike.route) }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Box {
            Routing(
                navHostController,
                innerPadding,
                appComponent,
                userComponent,
                { showBottomBar() },
                { hideBottomBar() })
        }
    }
}