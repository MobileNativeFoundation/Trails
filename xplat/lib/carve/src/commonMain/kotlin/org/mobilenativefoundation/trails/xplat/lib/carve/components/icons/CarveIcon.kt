package org.mobilenativefoundation.trails.xplat.lib.carve.components.icons

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.painterResource
import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.internal.CarveIconSets


@Composable
fun CarveIcon(
    icon: Icon,
    style: IconStyle,
) {
    val (iconSet, iconSetDescription) = when (style) {
        IconStyle.BOLD -> CarveIconSets.Bold to "Bold"
        IconStyle.LIGHT_OUTLINE -> CarveIconSets.LightOutline to "Light Outline"
    }

    val (resource, resourceDescription) = when (icon) {
        Icon.CHAT -> iconSet.chat to "Chat"
        Icon.HEART -> iconSet.heart to "Heart"
        Icon.HOME -> iconSet.home to "Home"
        Icon.LOCATION -> iconSet.location to "Location"
        Icon.NOTIFICATION -> iconSet.notification to "Notification"
        Icon.PLUS -> iconSet.plus to "Plus"
        Icon.PROFILE -> iconSet.profile to "Profile"
        Icon.SEARCH -> iconSet.search to "Search"
    }

    val painter = painterResource(resource)
    val contentDescription = "$iconSetDescription $resourceDescription Icon"

    Icon(painter, contentDescription)
}