package org.mobilenativefoundation.trails.shared.tig.theme.typography

import androidx.compose.material3.Typography
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import org.mobilenativefoundation.trails.shared.tig.R


@Stable
val DefaultHeadingFontFamily = FontFamily(
    Font(R.font.urbanist_bold)
)

@Stable
val DefaultFontFamily = FontFamily(
    Font(R.font.urbanist_regular)
)

private val defaultTypography = Typography()

val TrailsTypography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = DefaultHeadingFontFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = DefaultHeadingFontFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = DefaultHeadingFontFamily),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = DefaultHeadingFontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = DefaultHeadingFontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = DefaultHeadingFontFamily),
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = DefaultHeadingFontFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = DefaultHeadingFontFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = DefaultHeadingFontFamily),
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = DefaultFontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = DefaultFontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = DefaultFontFamily),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = DefaultFontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = DefaultFontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = DefaultFontFamily),
)

