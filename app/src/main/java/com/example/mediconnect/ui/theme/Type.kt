package com.example.mediconnect.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mediconnect.R

// Set of Material typography styles to start with


val balooFontType = FontFamily(
    Font(
        R.font.baloo_chettan_regular
    )
)

val arimaFontType = FontFamily(
    Font(
        R.font.arima_madurai_black,
        FontWeight.Black
    ),
    Font(
        R.font.arima_madurai_bold,
        FontWeight.Bold
    ),
    Font(
        R.font.arima_madurai_medium,
        FontWeight.Medium
    )
)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val BalooTypography = Typography(
    titleMedium = TextStyle(
        fontFamily = balooFontType,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    )
)

val ArimaTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = arimaFontType,
        fontWeight = FontWeight.Black,
        fontSize = 25.sp
    ),
    displayMedium = TextStyle(
        fontFamily = arimaFontType,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp
    ),
    displaySmall = TextStyle(
        fontFamily = arimaFontType,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )

)