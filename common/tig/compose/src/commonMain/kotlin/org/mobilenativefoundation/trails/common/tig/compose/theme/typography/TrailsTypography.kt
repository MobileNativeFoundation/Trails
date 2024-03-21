@file:OptIn(ExperimentalResourceApi::class)

package org.mobilenativefoundation.trails.common.tig.compose.theme.typography

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import trails.common.tig.compose.generated.resources.Res
import trails.common.tig.compose.generated.resources.urbanist_black
import trails.common.tig.compose.generated.resources.urbanist_black_italic
import trails.common.tig.compose.generated.resources.urbanist_bold_italic
import trails.common.tig.compose.generated.resources.urbanist_extra_bold
import trails.common.tig.compose.generated.resources.urbanist_extra_bold_italic
import trails.common.tig.compose.generated.resources.urbanist_extra_light
import trails.common.tig.compose.generated.resources.urbanist_extra_light_italic
import trails.common.tig.compose.generated.resources.urbanist_italic
import trails.common.tig.compose.generated.resources.urbanist_light
import trails.common.tig.compose.generated.resources.urbanist_light_italic
import trails.common.tig.compose.generated.resources.urbanist_medium
import trails.common.tig.compose.generated.resources.urbanist_medium_italic
import trails.common.tig.compose.generated.resources.urbanist_regular
import trails.common.tig.compose.generated.resources.urbanist_semi_bold
import trails.common.tig.compose.generated.resources.urbanist_semi_bold_italic
import trails.common.tig.compose.generated.resources.urbanist_thin
import trails.common.tig.compose.generated.resources.urbanist_thin_italic

@Stable
val DefaultHeadingFontFamily: FontFamily
    @Composable
    get() = FontFamily(headingFonts())

@Stable
val DefaultFontFamily: FontFamily
    @Composable
    get() = FontFamily(urbanistFonts())

private val defaultTypography = Typography()


val TrailsTypography: Typography
    @Composable
    get() = Typography(
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


@Composable
private fun urbanistFonts() = listOf(
    Font(Res.font.urbanist_black),
    Font(Res.font.urbanist_black_italic),
    Font(Res.font.urbanist_bold_italic),
    Font(Res.font.urbanist_extra_bold),
    Font(Res.font.urbanist_extra_bold_italic),
    Font(Res.font.urbanist_extra_light),
    Font(Res.font.urbanist_extra_light_italic),
    Font(Res.font.urbanist_italic),
    Font(Res.font.urbanist_light),
    Font(Res.font.urbanist_light_italic),
    Font(Res.font.urbanist_medium),
    Font(Res.font.urbanist_medium_italic),
    Font(Res.font.urbanist_regular),
    Font(Res.font.urbanist_semi_bold),
    Font(Res.font.urbanist_semi_bold_italic),
    Font(Res.font.urbanist_thin),
    Font(Res.font.urbanist_thin_italic),
)

@Composable
private fun headingFonts() = listOf(
    Font(Res.font.urbanist_thin),
)