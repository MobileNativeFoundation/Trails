package org.mobilenativefoundation.pokesocial.shared.pig.component.avatar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mobilenativefoundation.pokesocial.android.common.pig.R
import org.mobilenativefoundation.pokesocial.shared.pig.theme.PigTheme
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.darkColorScheme
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.lightColorScheme


@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    painter: Painter,
    size: AvatarSize = AvatarSize.Standard,
    contentDescription: String? = null,
) {
    Surface(
        modifier = modifier.size(size.value),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.onSurface
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
}


@Preview
@Composable
private fun StandardPreview() {
    Column {
        PigTheme(lightColorScheme()) {
            Surface {
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
            }
        }
    }
}