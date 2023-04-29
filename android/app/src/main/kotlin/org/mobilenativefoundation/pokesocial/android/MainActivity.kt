package org.mobilenativefoundation.pokesocial.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import org.mobilenativefoundation.pokesocial.shared.pig.theme.PIG
import org.mobilenativefoundation.pokesocial.shared.pig.theme.PigTheme

class MainActivity : ComponentActivity() {

    private val scope: CoroutineScope = CoroutineScope(Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PigTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text("Pokésocial", color = PIG.ColorScheme.onBackground, style = PIG.Typography.displayMedium)
                }
            }
        }
    }
}

