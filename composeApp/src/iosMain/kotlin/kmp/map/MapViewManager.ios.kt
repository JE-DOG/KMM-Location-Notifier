package kmp.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitInteropInteractionMode
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import ru.khinkal.locationNotifier.core.map.IosMapViewManager
import ru.khinkal.locationNotifier.core.map.MapViewManager

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun ExpectMap(
    modifier: Modifier,
    update: (MapViewManager) -> Unit,
    initialize: (MapViewManager) -> Unit,
) {
    var mapViewManager: IosMapViewManager? = remember { null }

    UIKitView(
        modifier = modifier,
        properties = UIKitInteropProperties(
            isNativeAccessibilityEnabled = true,
            interactionMode = UIKitInteropInteractionMode.NonCooperative,
        ),
        factory = {
            val newMapViewManager = IosMapViewManager()
            mapViewManager = newMapViewManager
            initialize(newMapViewManager)
            newMapViewManager.view
        },
        update = { update(requireNotNull(mapViewManager)) },
    )
}
