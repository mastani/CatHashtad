package app.mastani.cathashtad.designSystem

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.mastani.cathashtad.designSystem.theme.Gray4
import app.mastani.cathashtad.designSystem.theme.Gray_2
import app.mastani.cathashtad.designSystem.theme.Gray_3
import app.mastani.cathashtad.designSystem.theme.Gray_6
import app.mastani.cathashtad.designSystem.theme.sanFranciscoFamily

@Composable
fun CustomizableButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    @DrawableRes rightIconId: Int? = null,
    @DrawableRes leftIconId: Int? = null,
    iconModifier: Modifier = Modifier.size(16.dp),
    enabled: Boolean = true,
    isLoading: Boolean = false,
    color: Color = Gray4,
    textColor: Color = Color.White,
    type: ButtonType = ButtonType.Fill,
    size: ButtonSize = ButtonSize.LARGE,
    bgColor: Color = Color.White,
    horizontalPadding: Dp = 16.dp,
    radius: Dp = 8.dp,
    height: Dp? = null
) {
    val borderColor: Color = when (type) {
        ButtonType.Fill -> if (enabled) color else Gray_6
        ButtonType.OUTLINE -> if (enabled) color else Gray_6
        ButtonType.TEXT -> if (enabled) Color.Transparent else Color.Transparent
    }

    val backgroundColor: Color = when (type) {
        ButtonType.Fill -> if (enabled) color else Gray_6
        ButtonType.OUTLINE -> bgColor
        ButtonType.TEXT -> bgColor
    }

    val localTextColor: Color = when (type) {
        ButtonType.Fill -> if (enabled) textColor else Gray_2
        ButtonType.OUTLINE -> if (enabled) textColor else Gray_3
        ButtonType.TEXT -> if (enabled) textColor else Gray_3
    }

    val textStyle: TextStyle = when (size) {
        ButtonSize.SMALL -> MaterialTheme.typography.labelMedium.copy(fontFamily = sanFranciscoFamily)
        ButtonSize.MEDIUM -> MaterialTheme.typography.bodyMedium.copy(fontFamily = sanFranciscoFamily)
        ButtonSize.LARGE -> MaterialTheme.typography.bodyLarge.copy(fontFamily = sanFranciscoFamily)
        ButtonSize.XLARGE -> MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold, fontFamily = sanFranciscoFamily)
    }

    val localButtonHeight: Dp = when (size) {
        ButtonSize.SMALL -> 28.dp
        ButtonSize.MEDIUM -> 36.dp
        ButtonSize.LARGE -> 40.dp
        ButtonSize.XLARGE -> 44.dp
    }

    val loadingSize: Dp = when (size) {
        ButtonSize.SMALL -> 22.dp
        ButtonSize.MEDIUM -> 22.dp
        ButtonSize.LARGE -> 22.dp
        ButtonSize.XLARGE -> 22.dp
    }

    val shape = RoundedCornerShape(radius)

    Surface(
        modifier = modifier
            .height(height ?: localButtonHeight)
            .clip(shape = shape)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true, radius = radius, color = color),
                role = Role.Button,
                onClick = onClick,
                enabled = enabled && !isLoading
            ),
        color = backgroundColor,
        shape = shape
    ) {
        Row(
            modifier = Modifier.padding(horizontal = horizontalPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(loadingSize),
                    color = localTextColor,
                    strokeWidth = 1.5.dp
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    rightIconId?.let {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            modifier = iconModifier,
                            colorFilter = ColorFilter.tint(localTextColor)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                    }
                    Text(
                        text = text,
                        style = textStyle,
                        color = localTextColor
                    )
                    leftIconId?.let {
                        Spacer(modifier = Modifier.size(4.dp))
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            modifier = iconModifier,
                            colorFilter = ColorFilter.tint(localTextColor)
                        )
                    }
                }
            }
        }
    }
}

@Preview(backgroundColor = 0xffeeeeee)
@Composable
fun CustomizableButtonPrev(
    @PreviewParameter(CustomizableButtonPrevParameterProvider::class)
    parameters: ButtonParameter
) {
    CustomizableButton(
        onClick = {},
        text = parameters.text,
        enabled = parameters.enabled,
        isLoading = parameters.isLoading,
        color = parameters.color,
        textColor = parameters.textColor,
        type = parameters.type,
        size = parameters.size,
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)
    )
}

data class ButtonParameter(
    val text: String,
    val enabled: Boolean,
    val isLoading: Boolean,
    val color: Color,
    val textColor: Color = Color.White,
    val type: ButtonType,
    val size: ButtonSize
)

class CustomizableButtonPrevParameterProvider : PreviewParameterProvider<ButtonParameter> {
    override val values: Sequence<ButtonParameter>
        get() = sequenceOf(
            // FILL
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = true,
                isLoading = false,
                color = Color.Black,
                textColor = Color.White,
                type = ButtonType.Fill,
                size = ButtonSize.XLARGE
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = true,
                isLoading = false,
                color = Color.Black,
                textColor = Color.White,
                type = ButtonType.Fill,
                size = ButtonSize.LARGE
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = true,
                isLoading = false,
                color = Color.Black,
                textColor = Color.White,
                type = ButtonType.Fill,
                size = ButtonSize.MEDIUM
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = true,
                isLoading = false,
                color = Color.Black,
                textColor = Color.White,
                type = ButtonType.Fill,
                size = ButtonSize.SMALL

            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = false,
                isLoading = false,
                color = Color.Black,
                textColor = Color.White,
                type = ButtonType.Fill,
                size = ButtonSize.XLARGE
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = false,
                isLoading = false,
                color = Color.Black,
                textColor = Color.White,
                type = ButtonType.Fill,
                size = ButtonSize.LARGE
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = false,
                isLoading = false,
                color = Color.Black,
                textColor = Color.White,
                type = ButtonType.Fill,
                size = ButtonSize.MEDIUM
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = false,
                isLoading = false,
                color = Color.Black,
                textColor = Color.White,
                type = ButtonType.Fill,
                size = ButtonSize.SMALL
            ),
            // OUTLINE
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = true,
                isLoading = false,
                color = Color.Black,
                textColor = Color.Black,
                type = ButtonType.OUTLINE,
                size = ButtonSize.XLARGE
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = true,
                isLoading = false,
                color = Color.Black,
                textColor = Color.Black,
                type = ButtonType.OUTLINE,
                size = ButtonSize.LARGE
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = true,
                isLoading = false,
                color = Color.Black,
                textColor = Color.Black,
                type = ButtonType.OUTLINE,
                size = ButtonSize.MEDIUM
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = true,
                isLoading = false,
                color = Color.Black,
//                textColor = Color.Black,
                type = ButtonType.OUTLINE,
                size = ButtonSize.SMALL
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = false,
                isLoading = false,
                color = Color.Black,
                textColor = Color.Black,
                type = ButtonType.OUTLINE,
                size = ButtonSize.XLARGE
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = false,
                isLoading = false,
                color = Color.Black,
                textColor = Color.Black,
                type = ButtonType.OUTLINE,
                size = ButtonSize.LARGE
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = false,
                isLoading = false,
                color = Color.Black,
                textColor = Color.Black,
                type = ButtonType.OUTLINE,
                size = ButtonSize.MEDIUM
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = false,
                isLoading = false,
                color = Color.Black,
                textColor = Color.Black,
                type = ButtonType.OUTLINE,
                size = ButtonSize.SMALL
            ),
            // TEXT
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = true,
                isLoading = false,
                color = Color.Black,
                textColor = Color.Black,
                type = ButtonType.TEXT,
                size = ButtonSize.XLARGE
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = true,
                isLoading = false,
                color = Color.Black,
                textColor = Color.Black,
                type = ButtonType.TEXT,
                size = ButtonSize.LARGE
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = true,
                isLoading = false,
                color = Color.Black,
                textColor = Color.Black,
                type = ButtonType.TEXT,
                size = ButtonSize.MEDIUM
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = true,
                isLoading = false,
                color = Color.Black,
                textColor = Color.Black,
                type = ButtonType.TEXT,
                size = ButtonSize.SMALL

            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = false,
                isLoading = false,
                color = Color.Black,
                textColor = Color.Black,
                type = ButtonType.TEXT,
                size = ButtonSize.XLARGE
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = false,
                isLoading = false,
                color = Color.Black,
                textColor = Color.Black,
                type = ButtonType.TEXT,
                size = ButtonSize.LARGE
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = false,
                isLoading = false,
                color = Color.Black,
                textColor = Color.Black,
                type = ButtonType.TEXT,
                size = ButtonSize.MEDIUM
            ),
            ButtonParameter(
                text = BUTTON_TEXT,
                enabled = false,
                isLoading = false,
                color = Color.Black,
                textColor = Color.Black,
                type = ButtonType.TEXT,
                size = ButtonSize.SMALL
            )
        )

    companion object {
        const val BUTTON_TEXT = "Hello"
    }
}

enum class ButtonType { Fill, OUTLINE, TEXT }
enum class ButtonSize { SMALL, MEDIUM, LARGE, XLARGE }

