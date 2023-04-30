package org.mobilenativefoundation.trails.android.ui

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.mobilenativefoundation.trails.shared.navigation.R
import org.mobilenativefoundation.trails.shared.tig.theme.color.TrailsColors

@Composable
fun FAB(
    onClick: () -> Unit
) {
    val trailsColors = TrailsColors()

    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = trailsColors.white,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.location),
            contentDescription = "Location",
            tint = trailsColors.white,
            modifier = Modifier.size(32.dp)
        )
    }
}



