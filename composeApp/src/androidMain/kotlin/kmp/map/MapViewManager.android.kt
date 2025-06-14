package kmp.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import org.maplibre.android.MapLibre
import org.maplibre.android.maps.MapView
import ru.khinkal.locationNotifier.core.map.AndroidMapViewManager
import ru.khinkal.locationNotifier.core.map.MapViewManager

@Composable
actual fun ExpectMap(
    modifier: Modifier,
    update: (MapViewManager) -> Unit,
    initialize: (MapViewManager) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var mapViewManager: AndroidMapViewManager? = remember { null }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            if (!MapLibre.hasInstance()) {
                MapLibre.getInstance(context)
            }
            MapView(context).apply {
                val newMapViewManager = AndroidMapViewManager(this)
                mapViewManager = newMapViewManager
                newMapViewManager.registerLifecycle(lifecycleOwner.lifecycle)
                initialize(newMapViewManager)
            }
        },
        update = { update(requireNotNull(mapViewManager)) },
    )
}
