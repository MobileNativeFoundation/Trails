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
        Icon.HOME -> iconSet.home to "Home"
    }

    val painter = painterResource(resource)
    val contentDescription = "$iconSetDescription $resourceDescription Icon"

    Icon(painter, contentDescription)
}