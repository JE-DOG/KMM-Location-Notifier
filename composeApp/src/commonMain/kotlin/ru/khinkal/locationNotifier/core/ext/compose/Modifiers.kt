package ru.khinkal.locationNotifier.core.ext.compose

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier

inline fun Modifier.clickableWithoutRipple(
    noinline onClick: () -> Unit,
) = clickable(
        interactionSource = null,
        indication = null,
        onClick = onClick,
    )
