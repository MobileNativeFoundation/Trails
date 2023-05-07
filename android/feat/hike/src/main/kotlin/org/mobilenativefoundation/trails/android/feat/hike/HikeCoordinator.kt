package org.mobilenativefoundation.trails.android.feat.hike

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mobilenativefoundation.trails.shared.tig.theme.TigTheme
import org.mobilenativefoundation.trails.shared.tig.theme.color.TrailsColors

@Composable
fun HikeCoordinator() {
    Column {
        Box {
            MapView()
            NextWaypointTopBanner()

            Column(
                modifier = Modifier.fillMaxSize().padding(bottom = 72.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NextWaypointBottomBanner()
            }
        }
    }
}

@Composable
fun NextWaypointTopBanner() {
    // TODO()

    val colors = TrailsColors(true)

    Row(
        modifier = Modifier.fillMaxWidth()
            .background(colors.lightAlertBackground)
            .padding(horizontal = 36.dp, vertical = 48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painterResource(R.drawable.arrow_up),
            contentDescription = "Arrow up",
            modifier = Modifier.size(36.dp).rotate(25f),
            tint = colors.dark2
        )

        Spacer(modifier = Modifier.width(36.dp))

        Column {
            Text(
                "Towards",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Thin,
                color = colors.dark2
            )
            Text(
                "First Brother",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = colors.dark2
            )
        }
    }
}

@Composable
fun ColumnScope.NextWaypointBottomBanner() {
    val colors = TrailsColors()

    Row(
        modifier = Modifier.fillMaxWidth(.50f).clip(RoundedCornerShape(12.dp)).align(Alignment.CenterHorizontally)
            .background(colors.lightAlertBackground)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("30 min", color = colors.dark2, style = MaterialTheme.typography.titleMedium)
            Row {
                Text("1 mi", color = colors.dark2)
                Spacer(modifier = Modifier.width(12.dp))
                Text("12:00 pm", color = colors.dark2)
            }
        }


        Icon(
            painterResource(R.drawable.close_square_curved),
            contentDescription = "Close",
            modifier = Modifier.size(36.dp),
            tint = colors.error
        )

    }
}

@Composable
fun MapView() {

    // TODO()

    Image(
        painter = painterResource(R.drawable.three_brothers_map_view),
        contentDescription = "Big Slide Mountain via The Brothers",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun StandardPreview() {
    TigTheme {
        HikeCoordinator()
    }
}

