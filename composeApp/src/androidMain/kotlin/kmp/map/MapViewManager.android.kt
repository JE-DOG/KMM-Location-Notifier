package kmp.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import io.openmobilemaps.mapscore.map.view.MapView
import io.openmobilemaps.mapscore.shared.map.MapConfig
import io.openmobilemaps.mapscore.shared.map.coordinates.CoordinateSystemFactory
import ru.khinkal.locationNotifier.core.map.AndroidMapViewManager
import ru.khinkal.locationNotifier.core.map.MapViewManager

@Composable
actual fun ExpectMap(
    modifier: Modifier,
    update: (MapViewManager) -> Unit,
    initialize: (MapViewManager) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var mapViewManager: MapViewManager? = remember { null }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            MapView(context).apply {
                setupMap(MapConfig(CoordinateSystemFactory.getEpsg4326System()))
                registerLifecycle(lifecycleOwner.lifecycle)
                val newMapViewManager = AndroidMapViewManager(this)
                mapViewManager = newMapViewManager
                initialize(newMapViewManager)
            }
        },
        update = { update(requireNotNull(mapViewManager)) },
    )
}
