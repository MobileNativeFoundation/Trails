import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.mobilenativefoundation.trails.common.tig.compose.theme.color.Colors

enum class TrailsAccentColor {
    WHITE, BLACK, RED, PINK, PURPLE, DEEP_PURPLE, INDIGO, BLUE, LIGHT_BLUE, CYAN, TEAL, GREEN, LIGHT_GREEN, LIME, YELLOW, AMBER, ORANGE, DEEP_ORANGE, BROWN, BLUE_GRAY, GRAY
}

fun trailsColors(isLight: Boolean) = Colors(
    primary500 = Color(0xff52b69a),
    primary400 = Color(0xff5FBCA2),
    primary300 = Color(0xff6BC1A9),
    primary200 = Color(0xff77C6B0),
    primary100 = Color(0xff84CBB7),
    secondary500 = Color(0xff168AAD),
    secondary400 = Color(0xff1999C0),
    secondary300 = Color(0xff1BA9D3),
    secondary200 = Color(0xff23B6E2),
    secondary100 = Color(0xff37BCE5),
    success = Color(0xff4ADE80),
    info = Color(0xff246BFD),
    warning = Color(0xffFACC15),
    error = Color(0xffF75555),
    disabled = Color(0xffD8D8D8),
    disabledButton = Color(0xffDF485E),
    gray900 = Color(0xff212121),
    gray800 = Color(0xff424242),
    gray700 = Color(0xff616161),
    gray600 = Color(0xff757575),
    gray500 = Color(0xff9E9E9E),
    gray400 = Color(0xffBDBDBD),
    gray300 = Color(0xffE0E0E0),
    gray200 = Color(0xffEEEEEE),
    gray100 = Color(0xffF5F5F5),
    gray50 = Color(0xffFAFAFA),
    dark1 = Color(0xff181A20),
    dark2 = Color(0xff1F222A),
    dark3 = Color(0xff35383F),
    white = Color(0xffFFFFFF),
    black = Color(0xff000000),
    red = Color(0xffF44336),
    pink = Color(0xffE91E63),
    purple = Color(0xff9C27B0),
    deepPurple = Color(0xff673AB7),
    indigo = Color(0xff3F51B5),
    blue = Color(0xff2196F3),
    lightBlue = Color(0xff03A9F4),
    cyan = Color(0xff00BCD4),
    teal = Color(0xff009688),
    green = Color(0xff4CAF50),
    lightGreen = Color(0xff8BC34A),
    lime = Color(0xffCDDC39),
    yellow = Color(0xffFFEB3B),
    amber = Color(0xffFFC107),
    orange = Color(0xffFF9800),
    deepOrange = Color(0xffFF5722),
    brown = Color(0xff795548),
    blueGray = Color(0xff607D8B),
    redBackground = Color(0xffFFF1F3),
    purpleBackground = Color(0xffF4ECFF),
    blueBackground = Color(0xffF6FAFD),
    greenBackground = Color(0xffF2FFFC),
    orangeBackground = Color(0xffFFF8ED),
    pinkBackground = Color(0xffFFF5F5),
    yellowBackground = Color(0xffFFFEE0),
    redTransparent = Color(0xffFF4D67).copy(alpha = 0.08f),
    purpleTransparent = Color(0xff7210FF).copy(alpha = 0.08f),
    blueTransparent = Color(0xff335EF7).copy(alpha = 0.08f),
    orangeTransparent = Color(0xffFF9800).copy(alpha = 0.08f),
    yellowTransparent = Color(0xffFACC15).copy(alpha = 0.08f),
    greenTransparent = Color(0xff4CAF50).copy(alpha = 0.08f),
    cyanTransparent = Color(0xff00BCD4).copy(alpha = 0.08f),
    redGradient = Colors.Gradient(Color(0xffFF4D67), Color(0xffFF8395), Colors.Gradient.Direction.Linear),
    yellowGradient = Colors.Gradient(Color(0xffFACC15), Color(0xffFFE580), Colors.Gradient.Direction.Linear),
    purpleGradient = Colors.Gradient(Color(0xff7210FF), Color(0xff9D59FF), Colors.Gradient.Direction.Linear),
    blueGradient = Colors.Gradient(Color(0xff335EF7), Color(0xff5F82FF), Colors.Gradient.Direction.Linear),
    greenGradient = Colors.Gradient(Color(0xff22BB9C), Color(0xff35DEBC), Colors.Gradient.Direction.Linear),
    orangeGradient = Colors.Gradient(Color(0xffFB9400), Color(0xffFFAB38), Colors.Gradient.Direction.Linear),
    isLight = isLight
)


fun Colors.asColorScheme(): ColorScheme = ColorScheme(
    primary = primary500,
    onPrimary = white,
    primaryContainer = if (isLight) white else dark2,
    onPrimaryContainer = if (isLight) gray800 else gray100,
    inversePrimary = primary100,
    secondary = secondary500,
    onSecondary = white,
    secondaryContainer = if (isLight) white else dark2,
    onSecondaryContainer = if (isLight) gray800 else gray100,
    tertiary = info,
    onTertiary = white,
    tertiaryContainer = if (isLight) white else dark2,
    onTertiaryContainer = if (isLight) gray800 else gray100,
    background = if (isLight) white else dark2,
    onBackground = if (isLight) gray800 else gray100,
    surface = if (isLight) gray50 else dark3,
    onSurface = if (isLight) dark1 else white,
    surfaceVariant = if (isLight) gray100 else dark3,
    onSurfaceVariant = if (isLight) gray800 else gray50,
    surfaceTint = if (isLight) gray800 else gray50,
    inverseSurface = if (isLight) gray800 else gray50,
    inverseOnSurface = if (isLight) gray50 else dark3,
    error = error,
    errorContainer = redBackground,
    onError = white,
    onErrorContainer = error,
    outline = if (isLight) white else dark1,
    outlineVariant = if (isLight) white else black,
    scrim = secondary300,
)


@Composable
fun trailsAccentColor(color: TrailsAccentColor): Color {
    val colors = trailsColors(!isSystemInDarkTheme())
    return when (color) {
        TrailsAccentColor.WHITE -> colors.white
        TrailsAccentColor.BLACK -> colors.black
        TrailsAccentColor.RED -> colors.red
        TrailsAccentColor.PINK -> colors.pink
        TrailsAccentColor.PURPLE -> colors.purple
        TrailsAccentColor.DEEP_PURPLE -> colors.deepPurple
        TrailsAccentColor.INDIGO -> colors.indigo
        TrailsAccentColor.BLUE -> colors.blue
        TrailsAccentColor.LIGHT_BLUE -> colors.lightBlue
        TrailsAccentColor.CYAN -> colors.cyan
        TrailsAccentColor.TEAL -> colors.teal
        TrailsAccentColor.GREEN -> colors.green
        TrailsAccentColor.LIGHT_GREEN -> colors.lightGreen
        TrailsAccentColor.LIME -> colors.lime
        TrailsAccentColor.YELLOW -> colors.yellow
        TrailsAccentColor.AMBER -> colors.amber
        TrailsAccentColor.ORANGE -> colors.orange
        TrailsAccentColor.DEEP_ORANGE -> colors.deepOrange
        TrailsAccentColor.BROWN -> colors.brown
        TrailsAccentColor.BLUE_GRAY -> colors.blueGray
        TrailsAccentColor.GRAY -> colors.gray400
    }
}
