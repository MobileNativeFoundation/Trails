package org.mobilenativefoundation.trails.xplat.lib.carve.components.icons

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.painterResource
import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.internal.CarveIconSets


@Composable
fun CarveIcon(
    icon: Icon,
    style: IconStyle,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
) {
    val (iconSet, iconSetDescription) = when (style) {
        IconStyle.BOLD -> CarveIconSets.Bold to "Bold"
        IconStyle.LIGHT_OUTLINE -> CarveIconSets.LightOutline to "Light Outline"
        IconStyle.CURVED -> CarveIconSets.Curved to "Curved"
    }

    val (resource, resourceDescription) = when (icon) {
        Icon.CHAT -> iconSet.chat to "Chat"
        Icon.HEART -> iconSet.heart to "Heart"
        Icon.HOME -> iconSet.home to "Home"
        Icon.LOCATION -> iconSet.location to "Location"
        Icon.MESSAGE -> iconSet.message to "Message"
        Icon.NOTIFICATION -> iconSet.notification to "Notification"
        Icon.PLUS -> iconSet.plus to "Plus"
        Icon.PROFILE -> iconSet.profile to "Profile"
        Icon.SEARCH -> iconSet.search to "Search"
        Icon.ACTIVITY -> iconSet.activity to "Activity"
        Icon.BOOKMARK -> iconSet.bookmark to "Bookmark"
        Icon.MORE_SQUARE -> iconSet.moreSquare to "More Square"
        Icon.SEND -> iconSet.send to "Send"
        Icon.TICK_SQUARE -> iconSet.tickSquare to "Tick Square"
    }

    val painter = painterResource(resource ?: error("Resource not found."))
    val contentDescription = "$iconSetDescription $resourceDescription Icon"

    Icon(painter, contentDescription, modifier = modifier, tint)
}