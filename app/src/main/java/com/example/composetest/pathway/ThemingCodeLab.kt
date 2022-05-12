package com.example.composetest.pathway

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetest.R
import com.example.composetest.pathway.Typography.ThemingTypography

@Composable
fun CustomTheme(dark: Boolean = false, content: @Composable () -> Unit) {
    MaterialTheme(
        content = content,
        colors = if (dark) DarkColors else LightColors,
        typography = ThemingTypography,
        shapes = ThemingShapes
    )
}

val Red700 = Color(0xffdd0d3c)
val Red800 = Color(0xffd00036)
val Red900 = Color(0xffc20029)
val Red200 = Color(0xfff297a2)
val Red300 = Color(0xffea6d7e)
private val LightColors = lightColors(

    primary = Red700,
    primaryVariant = Red900,
    onPrimary = Color.White,
    secondary = Red700,
    secondaryVariant = Red900,
    onSecondary = Color.White,
    error = Red800
)

private val DarkColors = darkColors(
    primary = Red300,
    primaryVariant = Red700,
    onPrimary = Color.Black,
    secondary = Red300,
    onSecondary = Color.Black,
    error = Red200
)

private val Montserrat = FontFamily(
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_medium, FontWeight.W500),
    Font(R.font.montserrat_semibold, FontWeight.W600),
)

private val Domain = FontFamily(
    Font(R.font.domine_regular),
    Font(R.font.domine_bold, FontWeight.Bold),
)

object Typography {
    val ThemingTypography = Typography(
        h4 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.W600,
            fontSize = 30.sp
        ),
        h5 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.W600,
            fontSize = 24.sp
        ),
        h6 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.W600,
            fontSize = 20.sp
        ),
        subtitle1 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.W600,
            fontSize = 16.sp
        ),
        subtitle2 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp
        ),
        body1 = TextStyle(
            fontFamily = Domain,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        body2 = TextStyle(
            fontFamily = Montserrat,
            fontSize = 14.sp
        ),
        button = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp
        ),
        caption = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        overline = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.W500,
            fontSize = 12.sp
        )
    )
}

private val ThemingShapes = Shapes(
    small = CutCornerShape(topStart = 8.dp),
    medium = CutCornerShape(topStart = 24.dp),
    large = CutCornerShape(8.dp)
)