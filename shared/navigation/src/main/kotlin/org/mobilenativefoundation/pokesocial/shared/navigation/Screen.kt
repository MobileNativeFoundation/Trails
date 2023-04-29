package org.mobilenativefoundation.pokesocial.shared.navigation

private const val HOME_ROUTE = "home"
private const val DISCOVER_ROUTE = "discover"
private const val ACTIVITY_ROUTE = "activity"
private const val PROFILE_ROUTE = "profile"

private const val HOME = "HOME"
private const val DISCOVER = "DISCOVER"
private const val ACTIVITY = "ACTIVITY"
private const val PROFILE = "PROFILE"

sealed class Screen(
    val route: String,
    val title: String,
    val iconSelected: Int,
    val iconNotSelected: Int
) {
    object Home : Screen(HOME_ROUTE, HOME, R.drawable.home, R.drawable.home_light)
    object Discover : Screen(DISCOVER_ROUTE, DISCOVER, R.drawable.discovery, R.drawable.discovery_light)
    object Activity : Screen(ACTIVITY_ROUTE, ACTIVITY, R.drawable.activity, R.drawable.activity_light)
    object Profile : Screen(PROFILE_ROUTE, PROFILE, R.drawable.profile, R.drawable.profile_light)
}