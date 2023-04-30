package org.mobilenativefoundation.trails.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import org.mobilenativefoundation.trails.android.ui.Scaffold
import org.mobilenativefoundation.trails.shared.tig.theme.TigTheme

class MainActivity : ComponentActivity() {

    private val scope: CoroutineScope = CoroutineScope(Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()

            TigTheme {
                Scaffold(modifier = Modifier, navHostController = navHostController)
            }
        }
    }
}
