package org.mobilenativefoundation.trails.android.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.mobilenativefoundation.trails.android.feat.hike.HikeCoordinator
import org.mobilenativefoundation.trails.shared.navigation.Screen

@Composable
fun Routing(
    navController: NavHostController,
    innerPadding: PaddingValues,
    showBottomBar: () -> Unit,
    hideBottomBar: () -> Unit,
) {
    NavHost(
        navController = navController, startDestination = Screen.Home.route, modifier = Modifier
            .padding(innerPadding)
    ) {
        composable(Screen.Home.route) {
            showBottomBar()
            // TODO(#4)
            Text(
                "Home",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayMedium
            )
        }

        composable(Screen.Saved.route) {
            showBottomBar()
            // TODO(#42)
            Text(
                "Saved",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayMedium
            )
        }

        composable(Screen.Activity.route) {
            showBottomBar()
            // TODO(#43)
            Text(
                "Activity",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayMedium
            )
        }

        composable(Screen.Profile.route) {
            showBottomBar()
            // TODO(#49)
            Text(
                "Profile",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayMedium
            )
        }

        composable(Screen.Hike.route) {
            hideBottomBar()
            HikeCoordinator()
        }
    }
}