package ru.khinkal.locationNotifier.core.map

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import org.maplibre.android.maps.MapView
import ru.khinkal.locationNotifier.core.map.components.AndroidMapController
import ru.khinkal.locationNotifier.core.map.components.AndroidMapViewMarkerLayer
import ru.khinkal.locationNotifier.core.map.components.MapController
import ru.khinkal.locationNotifier.core.map.components.MapViewHandler
import ru.khinkal.locationNotifier.core.map.components.MapViewMarkerLayer
import ru.khinkal.locationNotifier.core.map.components.MapViewProperty
import ru.khinkal.locationNotifier.core.map.ext.toGeoPoint

class AndroidMapViewManager(
    val mapView: MapView,
) : MapViewManager() {

    override val controller: MapController = AndroidMapController(mapView)
    override val markerLayer: MapViewMarkerLayer by lazy {
        AndroidMapViewMarkerLayer(mapView)
    }
    override val handler: MapViewHandler = MapViewHandler()

    init {
        setupMap()
    }

    fun registerLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(
            object : DefaultLifecycleObserver {
                override fun onCreate(owner: LifecycleOwner) = mapView.onCreate(null)
                override fun onDestroy(owner: LifecycleOwner) = mapView.onDestroy()
                override fun onPause(owner: LifecycleOwner) = mapView.onPause()
                override fun onResume(owner: LifecycleOwner) = mapView.onResume()
                override fun onStart(owner: LifecycleOwner) = mapView.onStart()
                override fun onStop(owner: LifecycleOwner) = mapView.onStop()
            }
        )
    }

    private fun setupMap() {
        setupMapProperties()
        setupMapGestureListener()
    }

    private fun setupMapProperties() {
        mapView.getMapAsync { map ->
            map.tileCacheEnabled = true
            map.setStyle(MapViewProperty.STYLE_URL)
            with(map.uiSettings) {
                isLogoEnabled = false
                isCompassEnabled = false
                isRotateGesturesEnabled = false
            }
        }
    }

    private fun setupMapGestureListener() {
        mapView.getMapAsync { map ->
            map.addOnMapClickListener { latLng ->
                handler.onClick(latLng.toGeoPoint())
                true
            }
        }
    }
}
