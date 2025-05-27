package app.mastani.cathashtad.designSystem

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import app.mastani.cathashtad.designSystem.ext.debounceClickable
import app.mastani.cathashtad.designSystem.theme.Gray

@Composable
fun CustomizableTopBar(
    title: String,
    @DrawableRes rightIconId: Int? = null,
    @DrawableRes leftIconId: Int? = null,
    buttonText: String? = null,
    textColor: Color = Blue,
    onRightIconClick: (() -> Unit)? = null,
    onLeftClick: (() -> Unit)? = null,
    isTitleCentered: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leftIconId != null) {
            Image(
                painter = painterResource(leftIconId),
                contentDescription = null,
                modifier = Modifier
                    .size(42.dp)
                    .debounceClickable { onLeftClick?.let { onClick -> onClick() } },
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
            )
        } else if (buttonText != null) {
            LabelButton(
                onClick = { onLeftClick?.let { onClick -> onClick() } },
                buttonText = buttonText,
                textColor = textColor
            )
        } else {
            Box(modifier = Modifier.size(32.dp))
        }
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f),
            contentAlignment = if (isTitleCentered) Alignment.CenterStart else Alignment.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Gray
                )
            )
        }
        if (rightIconId != null) {
            Image(
                painter = painterResource(rightIconId),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .debounceClickable { onRightIconClick?.let { onClick -> onClick() } }
            )
        } else {
            Box(modifier = Modifier.size(32.dp))
        }
    }
}

@Preview
@Composable
private fun CustomizableTopBarPrev(
    @PreviewParameter(TopBarPreviewParameterProvider::class) parameter: TopBarParameter
) {
    CustomizableTopBar(
        title = parameter.title,
        leftIconId = parameter.leftIconId,
        rightIconId = parameter.rightIconId,
        isTitleCentered = parameter.isTitleCentered
    )
}

data class TopBarParameter(
    val title: String,
    val rightIconId: Int?,
    val leftIconId: Int?,
    val buttonText: String?,
    val isTitleCentered: Boolean,
)

class TopBarPreviewParameterProvider : PreviewParameterProvider<TopBarParameter> {
    override val values: Sequence<TopBarParameter>
        get() = sequenceOf(
            TopBarParameter(
                title = TITLE,
                rightIconId = null,
                leftIconId = null,
                buttonText = null,
                isTitleCentered = false
            ),
            TopBarParameter(
                title = TITLE,
                rightIconId = null,
                leftIconId = R.drawable.ic_arrow_left,
                buttonText = null,
                isTitleCentered = false
            ),
            TopBarParameter(
                title = TITLE,
                rightIconId = R.drawable.ic_arrow_right,
                leftIconId = R.drawable.ic_arrow_left,
                buttonText = null,
                isTitleCentered = false
            ),
            TopBarParameter(
                title = TITLE,
                rightIconId = R.drawable.ic_arrow_right,
                leftIconId = null,
                buttonText = BUTTON_TEXT,
                isTitleCentered = true
            )
        )

    companion object {
        const val TITLE = "Header Title"
        const val BUTTON_TEXT = "Action"
    }
}