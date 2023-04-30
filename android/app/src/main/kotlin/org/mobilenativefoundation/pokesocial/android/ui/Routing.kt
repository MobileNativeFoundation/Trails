package org.mobilenativefoundation.pokesocial.android.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.mobilenativefoundation.pokesocial.shared.navigation.Screen

@Composable
fun Routing(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    NavHost(
        navController = navController, startDestination = Screen.Home.route, modifier = Modifier
            .padding(innerPadding)
    ) {
        composable(Screen.Home.route) {
            // TODO(#4)
            Text(
                "Home",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayMedium
            )
        }

        composable(Screen.Discover.route) {
            // TODO(#42)
            Text(
                "Discover",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayMedium
            )
        }

        composable(Screen.Activity.route) {
            // TODO(#43)
            Text(
                "Activity",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayMedium
            )
        }

        composable(Screen.Profile.route) {
            // TODO(#49)
            Text(
                "Profile",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
}