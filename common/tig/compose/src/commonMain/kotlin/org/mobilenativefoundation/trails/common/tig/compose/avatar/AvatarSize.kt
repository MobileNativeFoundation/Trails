package org.mobilenativefoundation.trails.common.tig.compose.avatar

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Represents the available sizes for an Avatar.
 *
 * @property value The [Dp] value representing the size of the Avatar.
 */
open class AvatarSize(internal val value: Dp) {

    /**
     * Extra small Avatar size (18.dp).
     */
    object ExtraSmall : AvatarSize(18.dp)

    /**
     * Small Avatar size (24.dp).
     */
    object Small : AvatarSize(24.dp)

    /**
     * Standard Avatar size (36.dp).
     */
    object Standard : AvatarSize(36.dp)

    /**
     * Large Avatar size (48.dp).
     */
    object Large : AvatarSize(48.dp)

    /**
     * Prominent Avatar size (72.dp).
     */
    object Prominent : AvatarSize(72.dp)

    companion object {
        /**
         * Returns an array of all available [AvatarSize] values.
         *
         * @return An array containing all [AvatarSize] values.
         */
        fun values() = arrayOf(
            ExtraSmall,
            Small,
            Standard,
            Large,
            Prominent,
        )
    }
}