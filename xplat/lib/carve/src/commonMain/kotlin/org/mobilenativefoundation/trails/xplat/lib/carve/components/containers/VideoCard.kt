package org.mobilenativefoundation.trails.xplat.lib.carve.components.containers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.CarveIcon
import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.Icon
import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.IconStyle
import org.mobilenativefoundation.trails.xplat.lib.carve.material3.Carve

@Composable
fun VideoCard(
    url: String,
    creatorAvatarURL: String,
    creatorHandle: String,
    creatorDisplayName: String,
    creatorIsVerified: Boolean,
    audioSource: String,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Column(modifier = modifier) {
        Box {

            // Media
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = url,
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )

            // Metadata

            VideoMetadataUI(
                creatorAvatarURL, creatorHandle, creatorDisplayName, creatorIsVerified, audioSource
            )
        }

        // Actions

        Row(
            modifier = Modifier.fillMaxWidth().background(Color.White).padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CarveIcon(icon = Icon.HEART, style = IconStyle.LIGHT_OUTLINE, modifier = Modifier.size(28.dp))
                CarveIcon(icon = Icon.CHAT, style = IconStyle.LIGHT_OUTLINE, modifier = Modifier.size(28.dp))
                CarveIcon(icon = Icon.SEND, style = IconStyle.CURVED, modifier = Modifier.size(28.dp))
            }


            CarveIcon(icon = Icon.BOOKMARK, style = IconStyle.CURVED, modifier = Modifier.size(28.dp))

        }

        // Stats

        Row(
            modifier = Modifier.fillMaxWidth().background(Color.White).padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                "63,777 likes",
                color = Color.Black,
                style = Carve.Typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

        }

        // Title

        Row(
            modifier = Modifier.fillMaxWidth().background(Color.White).padding(top = 8.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            val description = "to understand life we must go back to the beginning | LIFE ON OUR PLANET" // TODO

            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = Carve.Typography.bodyLarge.fontSize
                        )
                    ) {
                        append(creatorHandle)
                    }
                    append(" ") // Space between the creator handle and description
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Normal,
                            fontSize = Carve.Typography.bodyLarge.fontSize
                        )
                    ) {
                        append(description)
                    }
                })

        }

        // Comments

        Row(
            modifier = Modifier.fillMaxWidth().background(Color.White).padding(top = 8.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {


            Text(
                "View all 3,051 comments",
                color = Color.LightGray,
                style = Carve.Typography.bodyLarge,
                fontWeight = FontWeight.Normal
            )
        }

        // Date

        Row(
            modifier = Modifier.fillMaxWidth().background(Color.White).padding(top = 8.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {


            Text(
                "1 day ago",
                color = Color.LightGray,
                style = Carve.Typography.bodyMedium,
                fontWeight = FontWeight.Normal
            )
        }
    }


}

@Composable
private fun VideoMetadataUI(
    creatorAvatarURL: String,
    creatorHandle: String,
    creatorDisplayName: String,
    creatorIsVerified: Boolean,
    audioSource: String
) {

    val contentColor = Carve.ColorScheme.background
    val backgroundColor = Carve.ColorScheme.onBackground.copy(alpha = 0.4f)

    Row(
        modifier = Modifier.fillMaxWidth().background(
            color = backgroundColor
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // Avatar
            Avatar(creatorAvatarURL)


            Column {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Display name
                    Text(creatorDisplayName, color = contentColor)

                    // Is Verified
                    Icon(
                        Icons.Default.CheckCircle,
                        null,
                        tint = Carve.ColorScheme.inversePrimary,
                        modifier = Modifier.size(24.dp)
                    ) // TODO

                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Handle
                    Text(creatorHandle, color = contentColor)

                    // •
                    Text("•", color = contentColor)
                    // Audio source
                    Text("Original audio", color = contentColor) // TODO
                }
            }

        }

        // Three dots
        Icon(Icons.Default.MoreVert, null, tint = contentColor) // TODO
    }
}

@Composable
private fun Avatar(
    url: String
) {
    AsyncImage(url, null, contentScale = ContentScale.Crop, modifier = Modifier.size(40.dp).clip(CircleShape))
}