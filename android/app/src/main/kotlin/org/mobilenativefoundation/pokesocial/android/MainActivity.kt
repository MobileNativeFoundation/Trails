package org.mobilenativefoundation.pokesocial.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import org.mobilenativefoundation.pokesocial.android.ui.Scaffold
import org.mobilenativefoundation.pokesocial.shared.pig.theme.PigTheme

class MainActivity : ComponentActivity() {

    private val scope: CoroutineScope = CoroutineScope(Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()

            PigTheme {
                Scaffold(modifier = Modifier, navHostController = navHostController)
            }
        }
    }
}

