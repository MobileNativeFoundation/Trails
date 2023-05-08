package org.mobilenativefoundation.trails.android.feat.trail.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mobilenativefoundation.trails.android.feat.trail.R
import org.mobilenativefoundation.trails.shared.data.entity.LatLng
import org.mobilenativefoundation.trails.shared.data.entity.LatLngBounds
import org.mobilenativefoundation.trails.shared.data.entity.Review
import org.mobilenativefoundation.trails.shared.data.entity.Trail
import org.mobilenativefoundation.trails.shared.tig.theme.TigTheme
import org.mobilenativefoundation.trails.shared.tig.theme.color.darkColorScheme

@Composable
fun Trail(
    trail: Trail,
    onBookmark: () -> Unit,
    onOffline: () -> Unit,
    onShare: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        Gallery()

        Ratings(
            difficulty = trail.difficulty,
            rating = trail.rating,
            reviewsCount = trail.reviews.size
        )

        Text(
            trail.name,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            trail.location,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Details(
            lengthInFeet = trail.lengthInFeet,
            estimatedMinToFinish = trail.estimatedMinToFinish
        )

        Actions(
            onBookmark = onBookmark,
            onOffline = onOffline,
            onShare = onShare
        )

    }
}


@Composable
fun Gallery() {
    // TODO()
    Image(
        painter = painterResource(R.drawable.brooklyn_bridge_via_manhattan),
        contentDescription = "Brooklyn Bridge via Manhattan",
        modifier = Modifier.height(300.dp).fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}


@Composable
fun Ratings(
    difficulty: String,
    rating: Double,
    reviewsCount: Int
) {
    // TODO()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            difficulty,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Icon(
            painterResource(R.drawable.star),
            contentDescription = "Star",
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            rating.toString(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            "($reviewsCount)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun Details(
    lengthInFeet: Int,
    estimatedMinToFinish: Int
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            "Length",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        ) // TODO() Use string res
        Text(
            lengthOfHike(lengthInFeet),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            "Est.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        ) // TODO() Use string res
        Text(
            estimatedTimeToFinish(estimatedMinToFinish),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

/**
 * Returns length of hike in TODO() most appropriate unit
 */
fun lengthOfHike(lengthInFeet: Int): String = lengthInFeet.toString()// TODO()

/**
 * Returns estimated time to finish in TODO() most appropriate unit
 */
fun estimatedTimeToFinish(estimatedMinToFinish: Int): String = estimatedMinToFinish.toString() // TODO()


@Composable
fun Actions(
    onBookmark: () -> Unit,
    onOffline: () -> Unit,
    onShare: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Action(Action.Bookmark, onBookmark)
        Action(Action.Offline, onOffline)
        Action(Action.Share, onShare)
    }
}

@Composable
fun Action(action: Action, onClick: () -> Unit) {
    Row(modifier = Modifier.clickable { onClick() }, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(action.icon),
            contentDescription = action.label,
            modifier = Modifier.size(28.dp),
            tint = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(action.name, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onBackground)
    }
}

enum class Action(
    val icon: Int,
    val label: String
) {
    Bookmark(R.drawable.bookmark_light, "Bookmark"),
    Offline(R.drawable.download_light, "Offline"), // TODO() Add download icon
    Share(R.drawable.send_light, "Share") // TODO() Add share icon
}

private val FakeReview = Review(
    id = "",
    trailId = "",
    userId = "",
    rating = 5.0,
    content = ""
)

private val FakeTrail = Trail(
    id = "",
    name = "Brooklyn Bridge Walk via Manhattan",
    route = listOf(),
    bounds = LatLngBounds(
        southwest = LatLng(0f, 0f),
        northeast = LatLng(0f, 0f)
    ),
    difficulty = "Easy",
    rating = 4.6,
    reviews = listOf(FakeReview, FakeReview, FakeReview),
    location = "New York, NY",
    lengthInFeet = 1000,
    estimatedMinToFinish = 47
)


@Preview
@Composable
fun StandardPreview() {
    TigTheme {
        Trail(
            trail = FakeTrail,
            onBookmark = {},
            onShare = {},
            onOffline = {}
        )
    }
}

@Preview
@Composable
fun InversePreview() {
    TigTheme(darkColorScheme()) {
        Trail(
            trail = FakeTrail,
            onBookmark = {},
            onShare = {},
            onOffline = {}
        )
    }
}