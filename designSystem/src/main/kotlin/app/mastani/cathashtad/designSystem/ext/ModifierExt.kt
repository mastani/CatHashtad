package app.mastani.cathashtad.designSystem.ext

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}

@Composable
fun screenWidthDp() = with(LocalConfiguration.current) { this.screenWidthDp }


inline fun Modifier.debounceClickable(
    delayMillis: Long = 400,
    crossinline onClick: () -> Unit
): Modifier = composed {
    var job: Job? = null
    var lastClickTime by remember { mutableLongStateOf(0L) }
    val scope = rememberCoroutineScope()

    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        job?.cancel()

        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > delayMillis) {
            lastClickTime = currentTime
            job = scope.launch {
                onClick()
                delay(delayMillis)
            }
        }
    }
}