package org.mobilenativefoundation.pokesocial.android.ui

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.mobilenativefoundation.pokesocial.shared.navigation.R
import org.mobilenativefoundation.pokesocial.shared.pig.theme.color.PokesocialColors

@Composable
fun FAB(
    onClick: () -> Unit
) {
    val pokesocialColors = PokesocialColors()

    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = pokesocialColors.white,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.plus_light),
            contentDescription = "Plus"
        )
    }
}



