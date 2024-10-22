package org.mobilenativefoundation.trails.xplat.lib.carve.components.images

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import trails.xplat.lib.carve.generated.resources.Res
import trails.xplat.lib.carve.generated.resources.trails_text_logo

@Composable
fun TrailsTextLogo(modifier: Modifier = Modifier) {
    val painter = painterResource(Res.drawable.trails_text_logo)
    Image(painter, "Trails Text Logo", modifier = modifier)
}