package app.mastani.cathashtad.designSystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.mastani.cathashtad.designSystem.ext.debounceClickable

@Composable
fun LabelButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    textColor: Color,
    iconId: Int? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .debounceClickable { onClick?.let { it() } }
            .then(modifier)
    ) {
        Text(
            text = buttonText,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
            color = textColor
        )
        iconId?.let {
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}