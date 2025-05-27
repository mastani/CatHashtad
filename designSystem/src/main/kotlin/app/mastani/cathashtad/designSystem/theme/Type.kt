package app.mastani.cathashtad.designSystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import app.mastani.cathashtad.designSystem.R

val sanFranciscoFamily = FontFamily(
    Font(R.font.sf_pro_light, FontWeight.Light),
    Font(R.font.sf_pro_regular, FontWeight.Normal),
    Font(R.font.sf_pro_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.sf_pro_medium, FontWeight.Medium),
    Font(R.font.sf_pro_bold, FontWeight.Bold),
    Font(R.font.sf_pro_semi_bold, FontWeight.SemiBold),
    Font(R.font.sf_pro_extra_bold, FontWeight.ExtraBold)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = sanFranciscoFamily,
        fontSize = 96.sp,
        fontWeight = FontWeight.Light,
        lineHeight = 117.sp,
        letterSpacing = (-1.5).sp
    ),
    displayMedium = TextStyle(
        fontFamily = sanFranciscoFamily,
        fontSize = 60.sp,
        fontWeight = FontWeight.Light,
        lineHeight = 73.sp,
        letterSpacing = (-0.5).sp
    ),
    displaySmall = TextStyle(
        fontFamily = sanFranciscoFamily,
        fontSize = 48.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 59.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = sanFranciscoFamily,
        fontSize = 30.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 37.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = sanFranciscoFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 29.sp
    ),
    titleLarge = TextStyle(
        fontFamily = sanFranciscoFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = sanFranciscoFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = sanFranciscoFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = sanFranciscoFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = sanFranciscoFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    labelLarge = TextStyle(
        fontFamily = sanFranciscoFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 16.sp,
        letterSpacing = 1.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = sanFranciscoFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelSmall = TextStyle(
        fontFamily = sanFranciscoFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 16.sp,
        letterSpacing = 1.sp
    )
)