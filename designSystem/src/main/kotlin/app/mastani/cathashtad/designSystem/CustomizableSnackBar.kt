package app.mastani.cathashtad.designSystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.mastani.cathashtad.designSystem.ext.debounceClickable
import app.mastani.cathashtad.designSystem.theme.Error
import app.mastani.cathashtad.designSystem.theme.Error_5
import app.mastani.cathashtad.designSystem.theme.Error_8
import app.mastani.cathashtad.designSystem.theme.Gray4
import app.mastani.cathashtad.designSystem.theme.Gray_5
import app.mastani.cathashtad.designSystem.theme.Gray_8
import app.mastani.cathashtad.designSystem.theme.Success
import app.mastani.cathashtad.designSystem.theme.Success_5
import app.mastani.cathashtad.designSystem.theme.Success_8

@Composable
fun SnackBarHost(
    hostState: SnackbarHostState
) {
    SnackbarHost(
        hostState = hostState
    ) {
        CustomizableSnackBar(
            data = it.visuals as SnackbarVisualsWithType,
            onDismiss = { hostState.currentSnackbarData?.dismiss() }
        )
    }
}

@Composable
fun CustomizableSnackBar(
    data: SnackbarVisualsWithType,
    onDismiss: () -> Unit,
) {
    val color: Color = when (data.type) {
        SnackBarMessageType.MESSAGE -> Gray4
        SnackBarMessageType.ERROR -> Error
        SnackBarMessageType.SUCCESS -> Success
    }
    val borderColor: Color = when (data.type) {
        SnackBarMessageType.MESSAGE -> Gray_5
        SnackBarMessageType.ERROR -> Error_5
        SnackBarMessageType.SUCCESS -> Success_5
    }
    val backgroundColor: Color = when (data.type) {
        SnackBarMessageType.MESSAGE -> Gray_8
        SnackBarMessageType.ERROR -> Error_8
        SnackBarMessageType.SUCCESS -> Success_8
    }
    val iconId = when (data.type) {
        SnackBarMessageType.MESSAGE -> R.drawable.ic_message_circle
        SnackBarMessageType.SUCCESS -> R.drawable.ic_tick_circle
        SnackBarMessageType.ERROR -> R.drawable.ic_close_circle
    }

    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 60.dp)
            .fillMaxWidth()
            .heightIn(min = 36.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(4.dp)
            )
            .border(width = 0.5.dp, color = borderColor, shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier.weight(1f),
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                colorFilter = ColorFilter.tint(color)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = data.message,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = color,
                    fontWeight = FontWeight.Medium,
                ),
                lineHeight = 20.sp,
                maxLines = 3
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = stringResource(id = R.string.dismiss),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
            ),
            modifier = Modifier.debounceClickable { onDismiss() }
        )
    }
}

class SnackbarVisualsWithType(
    val msg: String,
    val typ: SnackBarMessageType
) : SnackbarVisuals {
    override val actionLabel: String?
        get() = "OK"
    override val duration: SnackbarDuration
        get() = SnackbarDuration.Short
    override val message: String
        get() = msg
    override val withDismissAction: Boolean
        get() = true
    val type: SnackBarMessageType
        get() = typ
}

data class SnackbarData(
    val message: String,
    val duration: SnackbarDuration,
    val type: SnackBarMessageType
) : SnackbarData {
    companion object {
        val PREVIEW = SnackbarData(
            message = "Hello!",
            duration = SnackbarDuration.Long,
            type = SnackBarMessageType.ERROR
        )
    }

    override val visuals: SnackbarVisuals
        get() = object : SnackbarVisuals {
            override val actionLabel: String?
                get() = null
            override val duration: SnackbarDuration
                get() = duration
            override val message: String
                get() = message
            override val withDismissAction: Boolean
                get() = actionLabel != null
        }

    override fun dismiss() {
    }

    override fun performAction() {
    }
}

enum class SnackBarMessageType {
    MESSAGE,
    ERROR,
    SUCCESS
}

data class SnackbarMessage(
    val message: String?,
    val type: SnackBarMessageType = SnackBarMessageType.ERROR,
    val dsCode: String? = null
)

@Preview(widthDp = 300, showBackground = true)
@Composable
fun CustomizableSnackBarPrev(
    @PreviewParameter(SnackbarPreviewParameterProvider::class) parameters: SnackbarVisualsWithType
) {
    CustomizableSnackBar(
        data = parameters,
        onDismiss = {}
    )
}

class SnackbarPreviewParameterProvider : PreviewParameterProvider<SnackbarVisualsWithType> {
    override val values: Sequence<SnackbarVisualsWithType>
        get() = sequenceOf(
            SnackbarVisualsWithType(
                msg = "Hello!",
                typ = SnackBarMessageType.MESSAGE
            ),
            SnackbarVisualsWithType(
                msg = "Hello!",
                typ = SnackBarMessageType.SUCCESS
            ),
            SnackbarVisualsWithType(
                msg = "Hello!",
                typ = SnackBarMessageType.ERROR
            )
        )
}