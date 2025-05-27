package app.mastani.cathashtad.designSystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.mastani.cathashtad.designSystem.theme.Gray

@Composable
fun CatDetailInfo(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier,
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal,
                color = Gray
            )
        )

        Spacer(modifier = Modifier.width(18.dp))

        Text(
            modifier = Modifier,
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        )
    }
}

@Preview
@Composable
fun CatDetailInfoPrev() {
    CatDetailInfo(
        title = "Cat Name",
        value = "Short hair"
    )
}