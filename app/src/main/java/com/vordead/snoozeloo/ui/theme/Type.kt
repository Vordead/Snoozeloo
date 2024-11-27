package com.vordead.snoozeloo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vordead.snoozeloo.R

val Montserrat = FontFamily(
    Font(
        resId = R.font.montserrat_light,
        weight = FontWeight.Light,
    ),
    Font(
        resId = R.font.montserrat_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.montserrat_semibold,
        weight = FontWeight.SemiBold
    ),
    Font(
        resId = R.font.montserrat_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.montserrat_extrabold,
        weight = FontWeight.ExtraBold,
    ),
)


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Montserrat,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Montserrat,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Montserrat,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)