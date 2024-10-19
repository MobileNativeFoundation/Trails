package org.mobilenativefoundation.trails.xplat.lib.carve.material3

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.mobilenativefoundation.trails.xplat.lib.carve.foundation.FontFamilies


internal object CarveTypography {
    val value: Typography
        @Composable
        get() {
            val interFontFamily = FontFamilies.InterFontFamily()
            return Typography(
                displayLarge = TextStyle(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 57.sp,
                    lineHeight = 64.sp,
                    letterSpacing = (-0.25).sp
                ),
                displayMedium = TextStyle(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 45.sp,
                    lineHeight = 52.sp,
                    letterSpacing = 0.sp
                ),
                displaySmall = TextStyle(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 36.sp,
                    lineHeight = 44.sp,
                    letterSpacing = 0.sp
                ),
                headlineLarge = TextStyle(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 32.sp,
                    lineHeight = 40.sp,
                    letterSpacing = 0.sp
                ),
                headlineMedium = TextStyle(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 28.sp,
                    lineHeight = 36.sp,
                    letterSpacing = 0.sp
                ),
                headlineSmall = TextStyle(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    letterSpacing = 0.sp
                ),
                titleLarge = TextStyle(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.sp
                ),
                titleMedium = TextStyle(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.15.sp
                ),
                titleSmall = TextStyle(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp
                ),
                bodyLarge = TextStyle(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.5.sp
                ),
                bodyMedium = TextStyle(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.25.sp
                ),
                bodySmall = TextStyle(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    letterSpacing = 0.4.sp
                ),
                labelLarge = TextStyle(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp
                ),
                labelMedium = TextStyle(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    letterSpacing = 0.5.sp
                ),
                labelSmall = TextStyle(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    lineHeight = 16.sp,
                    letterSpacing = 0.5.sp
                )
            )
        }
}