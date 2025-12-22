package ru.khinkal.locationNotifier.core.map.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmp.map.ExpectMap
import ru.khinkal.locationNotifier.core.map.MapViewManager
import ru.khinkal.locationNotifier.core.map.components.MapViewProperty

@Composable
fun Map(
    modifier: Modifier,
    update: (MapViewManager) -> Unit = {},
    initialize: (MapViewManager) -> Unit = {},
) {
    ExpectMap(
        modifier = modifier,
        update = update,
        initialize = { mapViewManager ->
            mapViewManager.apply {
                with(controller) {
                    setMaxZoom(MapViewProperty.MAX_ZOOM)
                    setMinZoom(MapViewProperty.MIN_ZOOM)
                }
            }
            initialize(mapViewManager)
        },
    )
}
