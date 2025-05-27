package app.mastani.cathashtad.designSystem

import android.app.Activity
import android.os.Build
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun StatusBar(color: Color = Color.White, isDarkTheme: Boolean = false) {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        setStatusBarColor(window, color.toArgb())
        window.navigationBarColor = color.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isDarkTheme
    }
}

fun setStatusBarColor(window: Window, color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) { // Android 15+
        window.decorView.setOnApplyWindowInsetsListener { view, insets ->
            view.setBackgroundColor(color)

            // Adjust padding to avoid overlap
            view.setPadding(0, 0, 0, 0)
            insets
        }
    } else {
        // For Android 14 and below
        window.statusBarColor = color
    }
}