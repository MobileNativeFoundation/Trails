package org.mobilenativefoundation.trails.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mobilenativefoundation.trails.android.R
import org.mobilenativefoundation.trails.android.feat.timeline.home.HomeTimelineView
import org.mobilenativefoundation.trails.android.feat.trail.ui.composable.TrailsFeed
import org.mobilenativefoundation.trails.shared.tig.theme.TigTheme
import org.mobilenativefoundation.trails.shared.tig.theme.color.darkColorScheme

@Composable
fun HomeTabScaffold(
    followingFeed: @Composable () -> Unit = { HomeTimelineView() },
    trailsFeed: @Composable () -> Unit = { TrailsFeed() },
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 24.dp, end = 24.dp, top = 24.dp)
            .fillMaxSize()
    ) {
        Text(
            "Trails",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        val activeTab = remember { mutableStateOf(Tab.Following) }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {

            Spacer(modifier = Modifier.width(24.dp))

            Row(horizontalArrangement = Arrangement.Center) {
                Tab(Tab.Following.label, activeTab.value == Tab.Following) {
                    activeTab.value = Tab.Following
                }

                Spacer(modifier = Modifier.width(8.dp))

                Tab(Tab.ForYou.label, activeTab.value == Tab.ForYou) {
                    activeTab.value = Tab.ForYou
                }

            }

            Icon(
                painter = painterResource(R.drawable.search_light),
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(24.dp)
            )


        }

        Spacer(modifier = Modifier.height(24.dp))


        when (activeTab.value) {
            Tab.Following -> followingFeed()
            Tab.ForYou -> trailsFeed()
        }
    }
}


@Composable
fun Tab(text: String, isActive: Boolean, onSelect: () -> Unit) {
    val color =
        if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
    val fontWeight = if (isActive) FontWeight.Bold else FontWeight.Light
    val thickness = if (isActive) 4.dp else 0.dp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .widthIn(max = 80.dp)
            .clickable { onSelect() }) {
        Text(
            text,
            color = color,
            fontWeight = fontWeight,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))
        Box(
            Modifier
                .height(thickness)
                .fillMaxWidth()
                .background(color = color)
        )
    }
}

enum class Tab(
    val id: Int,
    val label: String
) {
    Following(0, "Following"),
    ForYou(1, "For You")
}


@Preview
@Composable
private fun StandardPreview() {
    TigTheme {
        HomeTabScaffold(
            followingFeed = { FakeFeed() },
            trailsFeed = { FakeFeed(MaterialTheme.colorScheme.primary) }
        )
    }
}

@Preview
@Composable
private fun InversePreview() {
    TigTheme(darkColorScheme()) {
        HomeTabScaffold(
            followingFeed = { FakeFeed() },
            trailsFeed = { FakeFeed(MaterialTheme.colorScheme.primary) }
        )
    }
}


@Composable
private fun FakeFeed(color: Color = MaterialTheme.colorScheme.onBackground) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(5) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(color.copy(alpha = 0.4f))
            )
        }
    }
}
