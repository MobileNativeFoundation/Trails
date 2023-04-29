package org.mobilenativefoundation.pokesocial.shared.pig.component.avatar

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

open class AvatarSize(internal val value: Dp) {
    object ExtraSmall : AvatarSize(18.dp)
    object Small : AvatarSize(24.dp)
    object Standard : AvatarSize(36.dp)
    object Large : AvatarSize(48.dp)
    object Prominent : AvatarSize(72.dp)

    companion object {
        fun values() = arrayOf(
            ExtraSmall,
            Small,
            Standard,
            Large,
            Prominent,
        )
    }
}
