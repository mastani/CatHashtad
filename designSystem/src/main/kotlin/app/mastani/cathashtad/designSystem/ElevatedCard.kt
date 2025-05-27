package app.mastani.cathashtad.designSystem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ElevatedCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 8.dp,
    elevation: Dp = 6.dp,
    borderColor: Color? = null,
    borderWidth: Dp = 1.dp,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(cornerRadius),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        ),
        border = BorderStroke(
            width = borderWidth,
            color = borderColor ?: Color.Transparent
        )
    ) {
        content()
    }
}