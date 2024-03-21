package org.mobilenativefoundation.trails.common.tig.compose

import androidx.compose.foundation.layout.Column
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_6_PRO
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.junit.Rule
import org.mobilenativefoundation.trails.common.tig.compose.avatar.Avatars
import org.mobilenativefoundation.trails.common.tig.compose.theme.TigTheme
import org.mobilenativefoundation.trails.common.tig.compose.theme.color.darkColorScheme
import org.mobilenativefoundation.trails.common.tig.compose.theme.color.lightColorScheme
import kotlin.test.Test

class AvatarScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = PIXEL_6_PRO,
        renderingMode = SessionParams.RenderingMode.SHRINK
    )

    @Test
    @OptIn(ExperimentalResourceApi::class)
    fun light() {
        paparazzi.snapshot {
            Column {
                TigTheme(lightColorScheme()) {
                    Avatars()
                }
            }
        }
    }

    @Test
    @OptIn(ExperimentalResourceApi::class)
    fun dark() {
        paparazzi.snapshot {
            Column {
                TigTheme(darkColorScheme()) {
                    Avatars()
                }
            }
        }
    }
}

