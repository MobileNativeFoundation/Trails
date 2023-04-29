package org.mobilenativefoundation.pokesocial.shared.pig.component.avatar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mobilenativefoundation.pokesocial.android.common.pig.R
import org.mobilenativefoundation.pokesocial.shared.pig.theme.PigTheme
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.PokesocialColors
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.darkColorScheme
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.lightColorScheme


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
                PokesocialColors(isSystemInDarkTheme()).white
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


@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    painter: Painter,
    size: AvatarSize = AvatarSize.Standard,
    contentDescription: String? = null,
) {
    Avatar(modifier, painter, size, contentDescription) {}
}


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
        if (isOnline) MaterialTheme.colorScheme.primary else PokesocialColors(isSystemInDarkTheme()).gray400

    Box(
        modifier = modifier.clip(CircleShape).size(size.value / 3)
            .border(
                BorderStroke(borderStrokeWidth.dp, PokesocialColors(isSystemInDarkTheme()).white),
                shape = CircleShape
            )
            .background(
                backgroundColor
            )
    )
}

@Composable
private fun EditIcon(
    modifier: Modifier = Modifier,
    size: AvatarSize = AvatarSize.Standard
) {
    Icon(
        painter = painterResource(R.drawable.edit_square),
        contentDescription = "Edit",
        tint = MaterialTheme.colorScheme.primary,
        modifier = Modifier.size(size.value / 3)
    )
}

@Preview
@Composable
private fun StandardPreview() {
    Column {
        PigTheme(lightColorScheme()) {
            Surface {

                Column {
                    Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Avatar(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.ExtraSmall
                        )
                        Avatar(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Small
                        )
                        Avatar(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Standard
                        )
                        Avatar(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Large
                        )
                        Avatar(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Prominent
                        )
                    }

                    Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        AvatarWithOnlineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.ExtraSmall
                        )
                        AvatarWithOnlineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Small,
                        )
                        AvatarWithOnlineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Standard,
                        )
                        AvatarWithOnlineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Large,
                        )
                        AvatarWithOnlineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Prominent,
                        )
                    }

                    Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        AvatarWithOfflineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.ExtraSmall
                        )
                        AvatarWithOfflineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Small,
                        )
                        AvatarWithOfflineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Standard,
                        )
                        AvatarWithOfflineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Large,
                        )
                        AvatarWithOfflineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Prominent,
                        )
                    }

                    Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        AvatarWithEditIcon(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.ExtraSmall
                        )
                        AvatarWithEditIcon(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Small,
                        )
                        AvatarWithEditIcon(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Standard,
                        )
                        AvatarWithEditIcon(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Large,
                        )
                        AvatarWithEditIcon(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Prominent,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun InversePreview() {
    Column {
        PigTheme(darkColorScheme()) {
            Surface {

                Column {
                    Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Avatar(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.ExtraSmall
                        )
                        Avatar(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Small
                        )
                        Avatar(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Standard
                        )
                        Avatar(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Large
                        )
                        Avatar(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Prominent
                        )
                    }

                    Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        AvatarWithOnlineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.ExtraSmall
                        )
                        AvatarWithOnlineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Small,
                        )
                        AvatarWithOnlineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Standard,
                        )
                        AvatarWithOnlineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Large,
                        )
                        AvatarWithOnlineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Prominent,
                        )
                    }

                    Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        AvatarWithOfflineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.ExtraSmall
                        )
                        AvatarWithOfflineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Small,
                        )
                        AvatarWithOfflineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Standard,
                        )
                        AvatarWithOfflineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Large,
                        )
                        AvatarWithOfflineCircle(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Prominent,
                        )
                    }

                    Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        AvatarWithEditIcon(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.ExtraSmall
                        )
                        AvatarWithEditIcon(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Small,
                        )
                        AvatarWithEditIcon(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Standard,
                        )
                        AvatarWithEditIcon(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Large,
                        )
                        AvatarWithEditIcon(
                            painter = painterResource(R.drawable.misty),
                            size = AvatarSize.Prominent,
                        )
                    }
                }
            }
        }
    }
}
