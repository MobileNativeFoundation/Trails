package org.mobilenativefoundation.trails.xplat.lib.carve.foundation

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import trails.xplat.lib.carve.generated.resources.*


object FontFamilies {
    @Composable
    fun InterFontFamily() = FontFamily(
        Font(Res.font.inter_18pt_thin, FontWeight.Thin, FontStyle.Normal),
        Font(Res.font.inter_18pt_extralight, FontWeight.ExtraLight, FontStyle.Normal),
        Font(Res.font.inter_18pt_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
        Font(Res.font.inter_18pt_light, FontWeight.Light, FontStyle.Normal),
        Font(Res.font.inter_18pt_lightitalic, FontWeight.Light, FontStyle.Italic),
        Font(Res.font.inter_18pt_regular, FontWeight.Normal, FontStyle.Normal),
        Font(Res.font.inter_18pt_italic, FontWeight.Normal, FontStyle.Italic),
        Font(Res.font.inter_18pt_medium, FontWeight.Medium, FontStyle.Normal),
        Font(Res.font.inter_18pt_mediumitalic, FontWeight.Medium, FontStyle.Italic),
        Font(Res.font.inter_18pt_semibold, FontWeight.SemiBold, FontStyle.Normal),
        Font(Res.font.inter_18pt_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
        Font(Res.font.inter_18pt_bold, FontWeight.Bold, FontStyle.Normal),
        Font(Res.font.inter_18pt_bolditalic, FontWeight.Bold, FontStyle.Italic),
        Font(Res.font.inter_18pt_extrabold, FontWeight.ExtraBold, FontStyle.Normal),
        Font(Res.font.inter_18pt_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
        Font(Res.font.inter_18pt_black, FontWeight.Black, FontStyle.Normal),
        Font(Res.font.inter_18pt_blackitalic, FontWeight.Black, FontStyle.Italic)
    )
}
