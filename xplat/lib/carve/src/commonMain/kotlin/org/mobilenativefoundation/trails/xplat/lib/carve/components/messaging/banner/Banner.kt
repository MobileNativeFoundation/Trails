package org.mobilenativefoundation.trails.xplat.lib.carve.components.messaging.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.CarveIcon
import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.Icon
import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.IconStyle
import org.mobilenativefoundation.trails.xplat.lib.carve.material3.Carve

@Composable
fun Banner(
    headlineText: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    cornerRadius: BannerCornerRadius = BannerCornerRadius.MEDIUM,
    leadingArtwork: IconContent? = null,
    paragraphText: String? = null,
    headlineMaxLines: Int = 1,
    paragraphMaxLines: Int = 2,
    trailingButton: BannerTrailingButton? = null,
    colors: BannerColors? = null
) {

    val shape = when (cornerRadius) {
        BannerCornerRadius.MEDIUM -> RoundedCornerShape(12.dp)
        BannerCornerRadius.SMALL -> RoundedCornerShape(8.dp)
    }

    val containerColor = colors?.containerColor ?: Carve.ColorScheme.surfaceContainer
    val contentColor = colors?.contentColor ?: Carve.ColorScheme.onSurface
    val trailingButtonContainerColor = colors?.trailingButtonContainerColor ?: Carve.ColorScheme.primaryContainer
    val trailingButtonContentColor = colors?.trailingButtonContentColor ?: Carve.ColorScheme.onPrimaryContainer

    Card(
        modifier = modifier
            .background(color = containerColor)
            .clip(shape)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .conditionalModifier(onClick != null) { clickable { onClick?.invoke() } },
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth().padding(
                start = 16.dp,
                end = if (trailingButton is BannerTrailingIcon) 8.dp else 16.dp,
                top = 16.dp,
                bottom = 16.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,

                ) {

                if (leadingArtwork != null) {
                    CarveIcon(icon = leadingArtwork.icon, style = leadingArtwork.style, tint = leadingArtwork.tint)
                }


                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(
                        headlineText,
                        style = Carve.Typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        maxLines = headlineMaxLines,
                        color = contentColor
                    )

                    if (paragraphText != null) {
                        Text(
                            headlineText,
                            style = Carve.Typography.bodyMedium,
                            maxLines = paragraphMaxLines,
                            color = contentColor
                        )
                    }
                }

            }

            if (trailingButton != null) {
                when (trailingButton) {
                    is BannerTrailingIcon -> {
                        IconButton(
                            onClick = trailingButton.onClick,
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = trailingButtonContainerColor,
                                contentColor = trailingButtonContentColor
                            )
                        ) {
                            CarveIcon(icon = trailingButton.content.icon, style = trailingButton.content.style)
                        }
                    }
                }
            }
        }
    }
}

enum class BannerCornerRadius {
    MEDIUM,
    SMALL
}

sealed interface BannerTrailingButton

enum class ButtonType {
    PRIMARY,
    SECONDARY
}

data class BannerTrailingIcon(
    val content: IconContent,
    val onClick: () -> Unit,
    val buttonType: ButtonType
) : BannerTrailingButton

sealed interface ImageContent

data class IconContent(
    val icon: Icon,
    val style: IconStyle,
    val tint: Color,
    val contentDescription: String? = null,
    val modifier: Modifier = Modifier
) : ImageContent

data class BannerColors(
    val containerColor: Color,
    val contentColor: Color,
    val trailingButtonContainerColor: Color,
    val trailingButtonContentColor: Color
)

@Composable
fun Modifier.conditionalModifier(
    condition: Boolean,
    modifier: @Composable Modifier.() -> Modifier
): Modifier = if (condition) modifier() else this