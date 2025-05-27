package app.mastani.cathashtad.designSystem.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.mastani.cathashtad.designSystem.R

@Composable
fun CatFriendlyBar(
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    rating: Int,
    iconPainter: Painter = painterResource(id = R.drawable.ic_heart),
    selectedColor: Color = Color(0xFFFF2828),
    unselectedColor: Color = Color(0xFFA2ADB1)
) {
    Row(modifier = modifier.wrapContentSize()) {
        for (value in 1..5) {
            HeartIcon(
                size = size,
                painter = iconPainter,
                ratingValue = value,
                rating = rating,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HeartIcon(
    size: Dp,
    rating: Int,
    painter: Painter,
    ratingValue: Int,
    selectedColor: Color,
    unselectedColor: Color
) {
    val tint by animateColorAsState(
        targetValue = if (ratingValue <= rating) selectedColor else unselectedColor,
        label = ""
    )

    Icon(
        modifier = Modifier.size(size),
        painter = painter,
        contentDescription = null,
        tint = tint
    )
}

@Preview
@Composable
fun PreviewUserRatingBar() {
    val ratingState = 3
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CatFriendlyBar(
            rating = ratingState
        )
    }
}