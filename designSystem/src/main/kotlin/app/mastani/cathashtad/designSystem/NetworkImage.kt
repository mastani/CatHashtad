package app.mastani.cathashtad.designSystem

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.valentinilk.shimmer.shimmer

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    @DrawableRes placeholder: Int,
    contentScale: ContentScale = ContentScale.Fit,
    alignment: Alignment = Alignment.Center,
    contentDescription: String? = null,
    colorFilter: ColorFilter? = null,
) {
    if (LocalInspectionMode.current) {
        Image(
            modifier = modifier,
            painter = painterResource(placeholder),
            contentScale = contentScale,
            alignment = alignment,
            contentDescription = "Preview"
        )
    } else {
        val context = LocalContext.current
        val model = ImageRequest.Builder(context)
            .apply {
                data(url)
                decoderFactory(SvgDecoder.Factory())
                diskCacheKey(url)
                memoryCacheKey(url)
                crossfade(true)
                crossfade(100)
            }.build()

        SubcomposeAsyncImage(
            modifier = modifier,
            model = model,
            loading = {
                Box(modifier = modifier.shimmer())
            },
            contentDescription = contentDescription,
            contentScale = contentScale,
            colorFilter = colorFilter
        )
    }
}