package org.mobilenativefoundation.pokesocial.shared.ui.hoc

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
import org.mobilenativefoundation.pokesocial.shared.pig.component.avatar.Avatar
import org.mobilenativefoundation.pokesocial.shared.pig.theme.PigTheme
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.PokesocialColors
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.darkColorScheme
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.lightColorScheme
import org.mobilenativefoundation.pokesocial.shared.ui.R

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
    val pokesocialColors = PokesocialColors()

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

        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(pokesocialColors.gray200))

        content()

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = timeAgo, color = pokesocialColors.gray700, style = MaterialTheme.typography.bodySmall)
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
private fun StandardPreview() {
    val pokesocialColors = PokesocialColors()


    Column {
        PigTheme(lightColorScheme()) {
            Surface {
                Post(
                    userName = "Misty",
                    avatar = {
                        Avatar(
                            painter = painterResource(R.drawable.misty),
                            contentDescription = "Misty"
                        )
                    },
                    content = {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Text(text = "Hey trainers! \uD83C\uDF1F It's Misty, Cerulean City's Gym Leader! \uD83D\uDCA7 Just encountered a Grimer while exploring the city. Though I'm not a fan of its slimy appearance, it's important to remember that every Pokémon has its strengths and charm! \uD83D\uDC99 Embrace diversity, and keep on catching 'em all! \uD83C\uDF08 ")
                            Text(
                                text = "#AllPokemonMatter #GrimerLove #CeruleanCity",
                                color = pokesocialColors.blue
                            )

                            Row {
                                Image(
                                    painter = painterResource(R.drawable.ash_misty_brock),
                                    contentDescription = "Ash, Misty, and Brock",
                                    modifier = Modifier.size(80.dp).clip(RoundedCornerShape(16.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
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
private fun StandardImagePostPreview() {
    val pokesocialColors = PokesocialColors()


    Column {
        PigTheme(lightColorScheme()) {
            Surface {
                Post(
                    userName = "Misty",
                    avatar = {
                        Avatar(
                            painter = painterResource(R.drawable.misty),
                            contentDescription = "Misty"
                        )
                    },
                    content = {
                        Image(
                            painter = painterResource(R.drawable.misty_ash),
                            contentDescription = "Misty and Ash",
                            modifier = Modifier.width(380.dp).height(300.dp).clip(
                                RoundedCornerShape(16.dp)
                            )
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
private fun InversePreview() {
    val pokesocialColors = PokesocialColors()


    Column {
        PigTheme(darkColorScheme()) {
            Surface {
                Post(
                    userName = "Misty",
                    avatar = {
                        Avatar(
                            painter = painterResource(R.drawable.misty),
                            contentDescription = "Misty"
                        )
                    },
                    content = {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Text(text = "Hey trainers! \uD83C\uDF1F It's Misty, Cerulean City's Gym Leader! \uD83D\uDCA7 Just encountered a Grimer while exploring the city. Though I'm not a fan of its slimy appearance, it's important to remember that every Pokémon has its strengths and charm! \uD83D\uDC99 Embrace diversity, and keep on catching 'em all! \uD83C\uDF08 ")
                            Text(
                                text = "#AllPokemonMatter #GrimerLove #CeruleanCity",
                                color = pokesocialColors.blue
                            )

                            Row {
                                Image(
                                    painter = painterResource(R.drawable.ash_misty_brock),
                                    contentDescription = "Ash, Misty, and Brock",
                                    modifier = Modifier.size(80.dp).clip(RoundedCornerShape(16.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
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
private fun InverseImagePostPreview() {
    val pokesocialColors = PokesocialColors()


    Column {
        PigTheme(darkColorScheme()) {
            Surface {
                Post(
                    userName = "Misty",
                    avatar = {
                        Avatar(
                            painter = painterResource(R.drawable.misty),
                            contentDescription = "Misty"
                        )
                    },
                    content = {
                        Image(
                            painter = painterResource(R.drawable.misty_ash),
                            contentDescription = "Misty and Ash",
                            modifier = Modifier.width(380.dp).height(300.dp).clip(
                                RoundedCornerShape(16.dp)
                            )
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