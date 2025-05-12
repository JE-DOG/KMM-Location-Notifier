package kmp.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.khinkal.locationNotifier.core.map.MapViewManager

@Composable
expect fun ExpectMap(
    modifier: Modifier,
    update: (MapViewManager) -> Unit = {},
    initialize: (MapViewManager) -> Unit = {},
)
