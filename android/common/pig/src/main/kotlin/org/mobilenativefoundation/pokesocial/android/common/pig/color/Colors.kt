package org.mobilenativefoundation.pokesocial.android.common.pig.color

import androidx.compose.ui.graphics.Color

data class Colors(
    val primary500: Color,
    val primary400: Color,
    val primary300: Color,
    val primary200: Color,
    val primary100: Color,

    val secondary500: Color,
    val secondary400: Color,
    val secondary300: Color,
    val secondary200: Color,
    val secondary100: Color,

    val success: Color,
    val info: Color,
    val warning: Color,
    val error: Color,
    val disabled: Color,
    val disabledButton: Color,
    val gray900: Color,
    val gray800: Color,
    val gray700: Color,
    val gray600: Color,
    val gray500: Color,
    val gray400: Color,
    val gray300: Color,
    val gray200: Color,
    val gray100: Color,
    val gray50: Color,

    val dark1: Color,
    val dark2: Color,
    val dark3: Color,

    val white: Color,
    val black: Color,
    val red: Color,
    val pink: Color,
    val purple: Color,
    val deepPurple: Color,
    val indigo: Color,
    val blue: Color,
    val lightBlue: Color,
    val cyan: Color,
    val teal: Color,
    val green: Color,
    val lightGreen: Color,
    val lime: Color,
    val yellow: Color,
    val amber: Color,
    val orange: Color,
    val deepOrange: Color,
    val brown: Color,
    val blueGray: Color,

    val redBackground: Color,
    val purpleBackground: Color,
    val blueBackground: Color,
    val greenBackground: Color,
    val orangeBackground: Color,
    val pinkBackground: Color,
    val yellowBackground: Color,

    val redTransparent: Color,
    val purpleTransparent: Color,
    val blueTransparent: Color,
    val orangeTransparent: Color,
    val yellowTransparent: Color,
    val greenTransparent: Color,
    val cyanTransparent: Color,

    val redGradient: Gradient,
    val yellowGradient: Gradient,
    val purpleGradient: Gradient,
    val blueGradient: Gradient,
    val greenGradient: Gradient,
    val orangeGradient: Gradient,
    val isLight: Boolean
) {
    data class Gradient(
        val start: Color,
        val end: Color,
        val direction: Direction
    ) {
        enum class Direction {
            Linear,
            Radial,
            Angular
        }
    }
}