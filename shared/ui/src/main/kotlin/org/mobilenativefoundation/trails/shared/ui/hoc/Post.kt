package org.mobilenativefoundation.trails.shared.ui.hoc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mobilenativefoundation.trails.shared.tig.avatar.Avatar
import org.mobilenativefoundation.trails.shared.tig.theme.TigTheme
import org.mobilenativefoundation.trails.shared.tig.theme.color.TrailsColors
import org.mobilenativefoundation.trails.shared.tig.theme.color.darkColorScheme
import org.mobilenativefoundation.trails.shared.tig.theme.color.lightColorScheme
import org.mobilenativefoundation.trails.shared.ui.R

@Composable
fun Post(
    userName: String,
    avatar: @Composable () -> Unit,
    content: @Composable () -> Unit,
    timeAgo: String,
    likeCount: String,
    commentCount: String,
    showMoreInfo: () -> Unit,
    navigateToPostDetailView: () -> Unit
) {
    val trailsColors = TrailsColors()

    Column(
        modifier = Modifier.padding(16.dp).clickable { navigateToPostDetailView() },
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                avatar()
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = userName, style = MaterialTheme.typography.titleLarge)
            }

            Icon(
                painter = painterResource(R.drawable.more_circle_light),
                contentDescription = "More info circle",
                modifier = Modifier.clickable { showMoreInfo() }
            )
        }

        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(trailsColors.gray200))

        content()

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = timeAgo, color = trailsColors.gray700, style = MaterialTheme.typography.bodySmall)
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            // Likes

            Row {
                Icon(
                    painter = painterResource(R.drawable.heart),
                    contentDescription = "Heart",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = likeCount,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            // Comments

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.chat_light),
                    contentDescription = "Chat",
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = commentCount,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
private fun StandardPostPreview() {
    val trailsColors = TrailsColors()


    Column {
        TigTheme(lightColorScheme()) {
            Surface {
                Post(
                    userName = "Trot",
                    avatar = {
                        Avatar(
                            painter = painterResource(R.drawable.trot),
                            contentDescription = "Trot"
                        )
                    },
                    content = {
                        Image(
                            painter = painterResource(R.drawable.big_slide_via_the_brothers),
                            contentDescription = "Big Slide Mountain via The Brothers",
                            modifier = Modifier.width(380.dp).height(380.dp).clip(
                                RoundedCornerShape(16.dp)
                            ),
                            contentScale = ContentScale.Crop
                        )
                    },
                    timeAgo = "2 minutes ago",
                    likeCount = "1.5K",
                    commentCount = "700",
                    showMoreInfo = {},
                    navigateToPostDetailView = {}
                )
            }
        }
    }
}

@Preview
@Composable
private fun InversePostPreview() {
    val trailsColors = TrailsColors()


    Column {
        TigTheme(darkColorScheme()) {
            Surface {
                Post(
                    userName = "Trot",
                    avatar = {
                        Avatar(
                            painter = painterResource(R.drawable.trot),
                            contentDescription = "Trot"
                        )
                    },
                    content = {
                        Image(
                            painter = painterResource(R.drawable.big_slide_via_the_brothers),
                            contentDescription = "Big Slide Mountain via The Brothers",
                            modifier = Modifier.width(380.dp).height(380.dp).clip(
                                RoundedCornerShape(16.dp)
                            ),
                            contentScale = ContentScale.Crop
                        )
                    },
                    timeAgo = "2 minutes ago",
                    likeCount = "1.5K",
                    commentCount = "700",
                    showMoreInfo = {},
                    navigateToPostDetailView = {}
                )
            }
        }
    }
}
