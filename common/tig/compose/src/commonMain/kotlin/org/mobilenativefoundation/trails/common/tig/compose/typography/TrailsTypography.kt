package org.mobilenativefoundation.trails.common.tig.compose.typography

import androidx.compose.material3.Typography
import androidx.compose.runtime.Stable
import org.mobilenativefoundation.trails.common.res.font.defaultHeadingFontFamily
import org.mobilenativefoundation.trails.common.res.font.urbanistFontFamily


@Stable
val DefaultHeadingFontFamily = defaultHeadingFontFamily

@Stable
val DefaultFontFamily = urbanistFontFamily

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