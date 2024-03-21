package org.mobilenativefoundation.trails.common.tig.compose.avatar

import TrailsAccentColor
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.mobilenativefoundation.trails.common.tig.compose.theme.TigTheme
import org.mobilenativefoundation.trails.common.tig.compose.theme.color.darkColorScheme
import org.mobilenativefoundation.trails.common.tig.compose.theme.color.lightColorScheme
import trails.common.tig.compose.generated.resources.Res
import trails.common.tig.compose.generated.resources.edit_square
import trails.common.tig.compose.generated.resources.tag
import trailsAccentColor

/**
 * Avatar component that displays a circular image with a border and optional content.
 *
 * @param modifier The modifier to be applied to the Avatar.
 * @param painter The [Painter] to be used for the Avatar image.
 * @param size The size of the Avatar. Defaults to [AvatarSize.Standard].
 * @param contentDescription The content description for accessibility purposes.
 * @param content Optional composable content to be displayed at the bottom left of the Avatar.
 */
@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    painter: Painter,
    size: AvatarSize = AvatarSize.Standard,
    contentDescription: String? = null,
    content: @Composable () -> Unit,
) {
    Box {

        Surface(
            modifier = modifier.size(size.value),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            border = BorderStroke(
                1.dp,
                trailsAccentColor(TrailsAccentColor.WHITE)
            ),
        ) {
            Box {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = modifier.clip(CircleShape).fillMaxSize(),
                )
            }
        }

        val iconOffsetX = when (size.value) {
            AvatarSize.ExtraSmall.value -> 1.dp
            AvatarSize.Small.value -> 2.dp
            AvatarSize.Standard.value -> 4.dp
            AvatarSize.Large.value -> 6.dp
            else -> 8.dp
        }

        Box(modifier = Modifier.align(Alignment.BottomEnd).offset(iconOffsetX)) {
            content()
        }
    }
}


/**
 * Avatar component that displays a circular image with a border.
 *
 * @param modifier The modifier to be applied to the Avatar.
 * @param painter The [Painter] to be used for the Avatar image.
 * @param size The size of the Avatar. Defaults to [AvatarSize.Standard].
 * @param contentDescription The content description for accessibility purposes.
 */
@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    painter: Painter,
    size: AvatarSize = AvatarSize.Standard,
    contentDescription: String? = null,
) {
    Avatar(modifier, painter, size, contentDescription) {}
}


/**
 * Avatar component with an online status circle indicator.
 *
 * @param modifier The modifier to be applied to the Avatar.
 * @param painter The [Painter] to be used for the Avatar image.
 * @param size The size of the Avatar. Defaults to [AvatarSize.Standard].
 * @param contentDescription The content description for accessibility purposes.
 */
@Composable
fun AvatarWithOnlineCircle(
    modifier: Modifier = Modifier,
    painter: Painter,
    size: AvatarSize = AvatarSize.Standard,
    contentDescription: String? = null
) {
    Avatar(modifier, painter, size, contentDescription) {
        OnlineCircle(size = size)
    }
}


/**
 * Avatar component with an offline status circle indicator.
 *
 * @param modifier The modifier to be applied to the Avatar.
 * @param painter The [Painter] to be used for the Avatar image.
 * @param size The size of the Avatar. Defaults to [AvatarSize.Standard].
 * @param contentDescription The content description for accessibility purposes.
 */
@Composable
fun AvatarWithOfflineCircle(
    modifier: Modifier = Modifier,
    painter: Painter,
    size: AvatarSize = AvatarSize.Standard,
    contentDescription: String? = null
) {
    Avatar(modifier, painter, size, contentDescription) {
        OfflineCircle(size = size)
    }
}

/**
 * Avatar component with an edit icon.
 *
 * @param modifier The modifier to be applied to the Avatar.
 * @param painter The [Painter] to be used for the Avatar image.
 * @param size The size of the Avatar. Defaults to [AvatarSize.Standard].
 * @param contentDescription The content description for accessibility purposes.
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun AvatarWithEditIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    size: AvatarSize = AvatarSize.Standard,
    contentDescription: String? = null
) {
    Avatar(modifier, painter, size, contentDescription) {
        EditIcon(size = size)
    }
}

@Composable
private fun OfflineCircle(
    modifier: Modifier = Modifier,
    size: AvatarSize = AvatarSize.Standard
) = OnlineStatusCircle(modifier, false, size)

@Composable
private fun OnlineCircle(
    modifier: Modifier = Modifier,
    size: AvatarSize = AvatarSize.Standard
) = OnlineStatusCircle(modifier, true, size)

@Composable
private fun OnlineStatusCircle(
    modifier: Modifier = Modifier,
    isOnline: Boolean,
    size: AvatarSize = AvatarSize.Standard
) {

    val borderStrokeWidth = when {
        size.value == AvatarSize.Standard.value -> 1.5f
        size.value > AvatarSize.Standard.value -> 2f
        else -> 1f
    }

    val backgroundColor =
        if (isOnline) MaterialTheme.colorScheme.primary else trailsAccentColor(TrailsAccentColor.GRAY)

    Box(
        modifier = modifier.clip(CircleShape).size(size.value / 3)
            .border(
                BorderStroke(
                    borderStrokeWidth.dp, trailsAccentColor(TrailsAccentColor.WHITE)
                ),
                shape = CircleShape
            )
            .background(
                backgroundColor
            )
    )
}

@ExperimentalResourceApi
@Composable
private fun EditIcon(
    modifier: Modifier = Modifier,
    size: AvatarSize = AvatarSize.Standard
) {
    Icon(
        painter = painterResource(Res.drawable.edit_square),
        contentDescription = "Edit",
        tint = MaterialTheme.colorScheme.primary,
        modifier = modifier.size(size.value / 3)
    )
}


@Preview
@Composable
private fun Previews() {
    val colorSchemes = listOf(darkColorScheme(), lightColorScheme())

    Column {
        colorSchemes.forEach { colorScheme ->
            TigTheme(colorScheme) {
                Avatars()
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun Avatars() {
    Surface {

        Column {
            Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Avatar(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.ExtraSmall
                )
                Avatar(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Small
                )
                Avatar(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Standard
                )
                Avatar(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Large
                )
                Avatar(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Prominent
                )
            }

            Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AvatarWithOnlineCircle(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.ExtraSmall
                )
                AvatarWithOnlineCircle(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Small,
                )
                AvatarWithOnlineCircle(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Standard,
                )
                AvatarWithOnlineCircle(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Large,
                )
                AvatarWithOnlineCircle(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Prominent,
                )
            }

            Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AvatarWithOfflineCircle(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.ExtraSmall
                )
                AvatarWithOfflineCircle(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Small,
                )
                AvatarWithOfflineCircle(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Standard,
                )
                AvatarWithOfflineCircle(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Large,
                )
                AvatarWithOfflineCircle(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Prominent,
                )
            }

            Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AvatarWithEditIcon(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.ExtraSmall
                )
                AvatarWithEditIcon(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Small,
                )
                AvatarWithEditIcon(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Standard,
                )
                AvatarWithEditIcon(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Large,
                )
                AvatarWithEditIcon(
                    painter = painterResource(Res.drawable.tag),
                    size = AvatarSize.Prominent,
                )
            }
        }
    }
}