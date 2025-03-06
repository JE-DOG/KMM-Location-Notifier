package ru.khinkal.locationNotifier.shared.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object BaseTypography {

    val Normal_14_400
        @ReadOnlyComposable @Composable
        get() = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 17.sp,
        )

    val Normal_18_400
        @ReadOnlyComposable @Composable
        get() = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 17.sp,
        )

    val Medium_14_500
        @ReadOnlyComposable @Composable
        get() = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 17.sp,
        )
}