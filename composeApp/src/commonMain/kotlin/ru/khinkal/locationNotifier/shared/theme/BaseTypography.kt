package ru.khinkal.locationNotifier.shared.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object BaseTypography {

    val Normal_12_400
        @Composable
        get()  = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 17.sp,
        )

    val Normal_14_400
        @Composable
        get()  = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 17.sp,
        )


    val Medium_12_500
        @Composable
        get() = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 17.sp,
        )

    val Medium_14_500
        @Composable
        get() = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 17.sp,
        )

    val Medium_16_500
        @Composable
        get() = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 17.sp,
        )

    val Medium_18_500
        @Composable
        get() = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 17.sp,
        )

    val Medium_20_500
        @Composable
        get() = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 17.sp,
        )
}