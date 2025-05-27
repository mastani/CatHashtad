package app.mastani.cathashtad.designSystem.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Loading(
    modifier: Modifier = Modifier,
    containerModifier: Modifier = Modifier.fillMaxSize(),
    color: Color = Gray,
    strokeWidth: Dp = 1.5.dp,
    contentAlignment: Alignment = Alignment.Center
) {
    Box(
        modifier = containerModifier,
        contentAlignment = contentAlignment
    ) {
        CircularProgressIndicator(
            modifier = modifier.size(18.dp),
            color = color,
            strokeWidth = strokeWidth
        )
    }
}