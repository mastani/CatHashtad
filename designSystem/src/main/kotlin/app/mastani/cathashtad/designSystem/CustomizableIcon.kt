package app.mastani.cathashtad.designSystem

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun CustomizableIcon(
    @DrawableRes iconId: Int? = null,
    url: String? = null,
    modifier: Modifier,
    containerModifier: Modifier = Modifier,
    tintColor: Color? = null,
    contentScale: ContentScale = ContentScale.Fit,
) {
    Box(
        modifier = containerModifier,
        contentAlignment = Alignment.Center
    ) {
        if (url != null) {
            NetworkImage(
                url = url,
                modifier = modifier,
                colorFilter = tintColor?.let { ColorFilter.tint(it) },
                contentScale = contentScale,
                placeholder = R.drawable.placeholder
            )
        } else {
            iconId?.let {
                if (tintColor != null) {
                    Image(
                        painter = painterResource(id = iconId),
                        contentDescription = null,
                        modifier = modifier,
                        colorFilter = ColorFilter.tint(tintColor)
                    )
                } else {
                    Image(
                        painter = painterResource(id = iconId),
                        contentDescription = null,
                        modifier = modifier,
                    )
                }
            }
        }
    }
}